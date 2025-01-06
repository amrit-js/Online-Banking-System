<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Futuristic Login</title>
    <link rel="stylesheet" href="./css/styles.css">
</head>
<body>
<div class="container">
    <div class="form-container">
        <h1>Login</h1>
        <br>

        <% if (request.getParameter("error") != null) { %>
        <p class="error-message"><%= request.getParameter("error") %></p>
        <% } %>
        <form id="loginForm" action="LoginServlet" method="post">
            <div class="input-group">
                <input type="text" name="username" id="username" required placeholder=" ">
                <label for="username">Username</label>
            </div>
            <div class="input-group">
                <input type="password" name="password" id="password" required placeholder=" ">
                <label for="password">Password</label>
            </div>
            <button type="submit">Login</button>
        </form>
    </div>
</div>
</body>
</html>