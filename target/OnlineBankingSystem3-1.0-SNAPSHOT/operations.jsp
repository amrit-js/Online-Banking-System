<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Operations - Online Banking System</title>
    <link rel="stylesheet" href="./css/operations.css">
    <script src="./js/operations.js" defer></script>
    <script src="script.js" defer></script>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Abel&family=Arimo:ital,wght@0,400..700;1,400..700&family=Josefin+Sans:ital,wght@0,100..700;1,100..700&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&family=Syne:wght@400..800&display=swap" rel="stylesheet">
</head>
<body>
<header>
    <div class="container">
        <h1>Welcome to Your Dashboard!</h1>
        <nav>
            <ul>
                <li><a href="login.jsp">Home</a></li>
                <li><a href="account_overview.jsp">Account Overview</a></li>
                <li><a href="operations.jsp">Transfer Funds</a></li>
                <li><a href="register.jsp">Register User</a></li>
                <li><a href="#">Transaction History</a></li>
                <li><a href="#">Logout</a></li>
            </ul>
        </nav>
    </div>
</header>

<main class="container">
    <section class="card overview-card">
        <h2>Account Overview</h2>
        <p>Your current balance: <strong>$5,000.00</strong></p>
        <button onclick="alert('Feature coming soon!')">View Details</button>
    </section>

    <section class="card transfer-card">
        <h2>Transfer Funds</h2>
        <form id="transferForm" method="POST" action="OperationsServlet">
            <input type="hidden" name="action" value="transfer">
            <label for="recipient">Recipient Username:</label>
            <input type="text" id="recipient" name="recipient" required>

            <label for="amount">Amount:</label>
            <input type="number" id="amount" name="amount" required>

            <button type="submit">Transfer</button>
        </form>

        <% if (request.getAttribute("message") != null) { %>
        <p style="color:red;"><%= request.getAttribute("message") %></p>
        <% } %>
    </section>

    <section class="card history-card">
        <h2>Transaction History</h2>
        <ul id="transactionHistory">
            <%
                String transactionHistory = (String) request.getAttribute("transactionHistory");
                if (transactionHistory != null && !transactionHistory.isEmpty()) {
                    out.print(transactionHistory);
                } else {
            %>
            <li>No transactions found.</li>
            <%
                }
            %>
        </ul>
    </section>

</main>

</body>
</html>
