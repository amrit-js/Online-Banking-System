package org.example.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class loginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Hardcoded credentials for demonstration purposes
    private static final String VALID_USERNAME = "admin";
    private static final String VALID_PASSWORD = "password";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Check if the username and password match
        if (VALID_USERNAME.equals(username) && VALID_PASSWORD.equals(password)) {
            // Create a session for the user
            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            // Redirect to a welcome page or dashboard
            response.sendRedirect("welcome.jsp");
        } else {
            // If login fails, redirect back to login page with an error message
            String errorMessage = "Invalid username or password.";
            response.sendRedirect("login.jsp?error=" + errorMessage);
        }
    }
}
