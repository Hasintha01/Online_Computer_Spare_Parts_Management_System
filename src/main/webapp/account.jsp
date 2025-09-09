<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Account Details - Vaas Computers</title>
    <style>
        :root {
            --color1: #ff55e0;
            --color2: #a65bcb;
            --color3: hsl(0, 100%, 71%);
            --bgColor: #f5f5f5; 
            --panelColor: #ffffff;
            --text-light: #333333;
            --shadow: 0 8px 24px rgba(0, 0, 0, 0.1);
        }

        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body {
            font-family: 'Inter', -apple-system, BlinkMacSystemFont, sans-serif;
            background: linear-gradient(135deg, var(--bgColor), #e0e0e0);
            color: var(--text-light);
            line-height: 1.6;
            overflow-x: hidden;
        }

        .navbar {
            background: linear-gradient(to right, #ffffff, #f0f0f0);
            padding: 15px 20px;
            box-shadow: var(--shadow);
            position: sticky;
            top: 0;
            z-index: 1000;
        }

        .container-fluid {
            display: flex;
            justify-content: space-between;
            align-items: center;
            max-width: 1400px;
            margin: 0 auto;
        }

        .navbar-brand {
            color: var(--color1);
            font-size: 1.8em;
            font-weight: 700;
            text-decoration: none;
            transition: color 0.3s ease;
        }

        .navbar-brand:hover {
            color: #000000;
        }

        .nav-links {
            display: flex;
            align-items: center;
            gap: 20px;
        }

        .nav-link {
            color: var(--text-light);
            text-decoration: none;
            font-size: 1.1em;
            position: relative;
            transition: color 0.3s ease;
        }

        .nav-link::after {
            content: '';
            position: absolute;
            width: 0;
            height: 2px;
            bottom: -4px;
            left: 0;
            background: var(--color2);
            transition: width 0.3s ease;
        }

        .nav-link:hover::after,
        .nav-link.active::after {
            width: 100%;
        }

        .nav-link.active {
            color: var(--color2);
            font-weight: 600;
        }

        .navbar-text {
            color: #666666;
            font-size: 1em;
        }

        .account-container {
            max-width: 600px;
            margin: 40px auto;
            padding: 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
            gap: 30px;
        }

        .heading {
            background: linear-gradient(to right, var(--color1), var(--color2));
            color: #ffffff;
            width: 100%;
            text-align: center;
            padding: 15px;
            font-size: 2em;
            font-weight: 700;
            box-shadow: var(--shadow);
            border-radius: 10px;
            animation: slideIn 0.5s ease forwards;
        }

        @keyframes slideIn {
            from { transform: translateY(-50px); opacity: 0; }
            to { transform: translateY(0); opacity: 1; }
        }

        .account-details, .edit-form {
            background: var(--panelColor);
            padding: 30px;
            border-radius: 16px;
            box-shadow: var(--shadow);
            width: 100%;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }

        .account-details:hover, .edit-form:hover {
            transform: translateY(-5px);
            box-shadow: 0 12px 32px rgba(0, 0, 0, 0.15);
        }

        .detail-item {
            display: flex;
            justify-content: space-between;
            padding: 10px 0;
            border-bottom: 1px solid #e0e0e0;
        }

        .detail-label {
            font-weight: 600;
            color: var(--text-light);
        }

        .detail-value {
            color: #666666;
        }

        .edit-form p {
            font-size: 1.2em;
            font-weight: 600;
            margin-bottom: 15px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            display: block;
            font-size: 1em;
            font-weight: 500;
            margin-bottom: 8px;
            color: var(--text-light);
        }

        input[type="text"], input[type="email"], input[type="password"] {
            width: 100%;
            padding: 12px;
            border: none;
            border-radius: 8px;
            background: #f0f0f0;
            color: var(--text-light);
            font-size: 1em;
            transition: background 0.3s ease;
        }

        input:focus {
            background: #e0e0e0;
            outline: none;
        }

        .submit-button, .cancel-button, .delete-button {
            width: 100%;
            background: var(--color1);
            color: #ffffff;
            border: none;
            padding: 12px;
            font-size: 1.1em;
            font-weight: 600;
            cursor: pointer;
            border-radius: 8px;
            transition: all 0.3s ease;
            position: relative;
            overflow: hidden;
            margin-top: 10px;
        }

        .cancel-button {
            background: #999999;
        }

        .delete-button {
            background: #ff0000; /* Distinct red for delete */
        }

        .submit-button::before, .cancel-button::before, .delete-button::before {
            content: '';
            position: absolute;
            top: 0;
            left: -100%;
            width: 100%;
            height: 100%;
            background: linear-gradient(90deg, transparent, rgba(255, 255, 255, 0.3), transparent);
            transition: left 0.5s ease;
        }

        .submit-button:hover::before, .cancel-button:hover::before, .delete-button:hover::before {
            left: 100%;
        }

        .submit-button:hover {
            background: var(--color2);
            transform: translateY(-2px);
            box-shadow: 0 4px 12px rgba(0, 192, 96, 0.3);
        }

        .cancel-button:hover {
            background: #777777;
            transform: translateY(-2px);
        }

        .delete-button:hover {
            background: #cc0000; /* Darker red on hover */
            transform: translateY(-2px);
            box-shadow: 0 4px 12px rgba(255, 0, 0, 0.3);
        }

        .error-message {
            color: var(--color3);
            font-size: 0.9em;
            margin-top: 10px;
            text-align: center;
        }

        @media (max-width: 768px) {
            .account-container {
                width: 90%;
            }

            .heading {
                font-size: 1.6em;
                padding: 12px;
            }
        }
    </style>
</head>
<body>
    <% User user = (User) session.getAttribute("user"); %>
    <% if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    } %>
    <nav class="navbar">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Spare Parts Management</a>
            <div class="nav-links">

                <a class="nav-link active" href="account">My profile</a>
                <span class="navbar-text">Welcome, <%= user.getFirstName() %> <%= user.getLastName() %></span>
                <a class="nav-link" href="logout">Logout</a>
            </div>
        </div>
    </nav>

    <div class="heading">My profile</div>

    <div class="account-container">
        <div class="account-details">
            <div class="detail-item">
                <span class="detail-label">First Name:</span>
                <span class="detail-value"><%= user.getFirstName() %></span>
            </div>
            <div class="detail-item">
                <span class="detail-label">Last Name:</span>
                <span class="detail-value"><%= user.getLastName() %></span>
            </div>
            <div class="detail-item">
                <span class="detail-label">Email:</span>
                <span class="detail-value"><%= user.getEmail() %></span>
            </div>
        </div>

        <div class="edit-form">
            <p>Edit Account Details</p>
            <form action="account" method="post" id="editForm">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="userId" value="<%= user.getUserId() %>">
                <div class="form-group">
                    <label for="firstName">First Name</label>
                    <input type="text" id="firstName" name="firstName" value="<%= user.getFirstName() %>" required>
                </div>
                <div class="form-group">
                    <label for="lastName">Last Name</label>
                    <input type="text" id="lastName" name="lastName" value="<%= user.getLastName() %>" required>
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" value="<%= user.getEmail() %>" required>
                </div>
                <div class="form-group">
                    <label for="password">New Password (leave blank to keep current)</label>
                    <input type="password" id="password" name="password" placeholder="Enter new password">
                </div>
                <div class="form-group">
                    <label for="confirmPassword">Confirm New Password</label>
                    <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Confirm new password">
                </div>
                <button type="submit" class="submit-button">Update Account</button>
                <button type="button" class="cancel-button" onclick="window.location.href='account'">Cancel</button>
                <% String error = (String) request.getAttribute("error"); %>
                <% if (error != null) { %>
                    <p class="error-message"><%= error %></p>
                <% } %>
            </form>
            <!-- Delete Account Button -->
            <form action="account" method="post" style="margin-top: 20px;">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="userId" value="<%= user.getUserId() %>">
                <button type="submit" class="delete-button" onclick="return confirm('Are you sure you want to delete your account? This action cannot be undone.');">Delete Account</button>
            </form>
        </div>
    </div>

    <script>
        document.getElementById("editForm").addEventListener("submit", function(event) {
            const password = document.getElementById("password").value;
            const confirmPassword = document.getElementById("confirmPassword").value;
            if (password && password !== confirmPassword) {
                event.preventDefault();
                alert("Passwords do not match!");
            }
        });
    </script>
</body>
</html>