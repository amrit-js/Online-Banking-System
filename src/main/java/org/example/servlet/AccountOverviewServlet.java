package org.example.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/AccountOverview")
public class AccountOverviewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve current balance from session or set a default
        HttpSession session = request.getSession();
        Double accountBalance = (Double) session.getAttribute("accountBalance");
        if (accountBalance == null) {
            accountBalance = 5000.00; // Default balance if not set
            session.setAttribute("accountBalance", accountBalance);
        }

        // Handle adding amount
        String amountParam = request.getParameter("amount");
        double amount = 0;
        if (amountParam != null && !amountParam.isEmpty()) {
            try {
                amount = Double.parseDouble(amountParam);
                accountBalance += amount; // Add the amount to the current balance
                session.setAttribute("accountBalance", accountBalance); // Update session with new balance
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Invalid amount entered.");
            }
        }

        // Initialize recent transactions
        String[] recentTransactions = (String[]) session.getAttribute("recentTransactions");
        if (recentTransactions == null) {
            recentTransactions = new String[0]; // Initialize to empty array if null
        }
        // Add transaction entry after updating balance
        String newTransaction = "Deposit: $" + String.format("%.2f", amount);
        String[] updatedTransactions = new String[recentTransactions.length + 1];
        System.arraycopy(recentTransactions, 0, updatedTransactions, 0, recentTransactions.length);
        updatedTransactions[recentTransactions.length] = newTransaction;
        session.setAttribute("recentTransactions", updatedTransactions); // Update session with new transactions

        // Set attributes for JSP
        request.setAttribute("recentTransactions", recentTransactions);
        request.setAttribute("accountBalance", accountBalance);

        request.getRequestDispatcher("account_overview.jsp").forward(request, response);
    }
}
