package org.example.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/OperationsServlet")
public class OperationsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Database connection parameters
        String driver = "com.mysql.jdbc.Driver";
        String connectionUrl = "jdbc:mysql://localhost:3306/";
        String database = "test";
        String userid = "root";
        String password = "";
        HttpSession session = request.getSession();
        String action = request.getParameter("action");

        if ("transfer".equals(action)) {
            // Handle fund transfer
            String recipient = request.getParameter("recipient");
            double amount = Double.parseDouble(request.getParameter("amount"));
            String username = (String) session.getAttribute("username"); // Assuming username is stored in session

            // Logic to transfer funds (this is a simplified version)
            try {
                Class.forName(driver);
                Connection connection = DriverManager.getConnection(connectionUrl + database, userid, password);

                // Check if the recipient exists and has sufficient balance
                PreparedStatement checkRecipientStmt = connection.prepareStatement("SELECT * FROM users WHERE username=?");
                checkRecipientStmt.setString(1, recipient);
                ResultSet recipientResultSet = checkRecipientStmt.executeQuery();

                if (recipientResultSet.next()) {
                    // Proceed with transfer logic (deduct from sender and add to recipient)
                    PreparedStatement transferStmt = connection.prepareStatement("UPDATE accounts SET balance = balance - ? WHERE username=?");
                    transferStmt.setDouble(1, amount);
                    transferStmt.setString(2, username);
                    transferStmt.executeUpdate();

                    PreparedStatement depositStmt = connection.prepareStatement("UPDATE accounts SET balance = balance + ? WHERE username=?");
                    depositStmt.setDouble(1, amount);
                    depositStmt.setString(2, recipient);
                    depositStmt.executeUpdate();

                    // Log the transaction
                    PreparedStatement logTransactionStmt = connection.prepareStatement("INSERT INTO transaction (name, amount, date_time) VALUES (?, ?, NOW())");
                    logTransactionStmt.setString(1, username);
                    logTransactionStmt.setDouble(2, amount);
                    logTransactionStmt.executeUpdate();

                    request.setAttribute("message", "Transfer successful!");
                } else {
                    request.setAttribute("message", "Recipient not found.");
                }

                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("message", "Error during transfer: " + e.getMessage());
            }
        }

        // Retrieve transaction history
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(connectionUrl + database, userid, password);

            PreparedStatement transactionHistoryStmt = connection.prepareStatement("SELECT * FROM transaction WHERE name=? ORDER BY date_time DESC");
            String username = (String) session.getAttribute("username"); // Assuming username is stored in session
            transactionHistoryStmt.setString(1, username);
            ResultSet transactionResultSet = transactionHistoryStmt.executeQuery();

            StringBuilder transactionsHtml = new StringBuilder();
            while (transactionResultSet.next()) {
                transactionsHtml.append("<li>")
                        .append(transactionResultSet.getString("date_time"))
                        .append(": $")
                        .append(transactionResultSet.getString("amount"))
                        .append("</li>");
            }

            request.setAttribute("transactionHistory", transactionsHtml.toString());
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Forward to operations JSP page
        request.getRequestDispatcher("operations.jsp").forward(request, response);
    }
}
