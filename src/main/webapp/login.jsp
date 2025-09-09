<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login - Spare Parts Management</title>
    <style>
        :root {
            --color1: #2e8b57; /* Primary green for buttons and links */
            --color2: #246b45; /* Darker green for hover */
            --color3: #ff4d4d; /* Red for error messages */
            --bgColor: #f5f5f5; /* Light gray background */
            --card-bg: #ffffff; /* White card background */
            --text-light: #333333; /* Dark gray text for readability */
            --shadow: 0 8px 24px rgba(0, 0, 0, 0.1); /* Subtle shadow */
        }

        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            padding: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, var(--bgColor), #e0e0e0);
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        .login-container {
            background: var(--card-bg);
            padding: 40px 30px;
            border-radius: 12px;
            width: 100%;
            max-width: 400px;
            box-shadow: var(--shadow);
            animation: fadeIn 0.6s ease-in-out;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }

        .login-container:hover {
            transform: translateY(-5px);
            box-shadow: 0 12px 32px rgba(0, 0, 0, 0.15);
        }

        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(30px); }
            to { opacity: 1; transform: translateY(0); }
        }

        h2 {
            text-align: center;
            margin-bottom: 30px;
            color: var(--text-light);
            font-weight: 700;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 6px;
            font-weight: 600;
            color: var(--text-light);
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            background: #f0f0f0;
            padding: 10px 12px;
            border: none;
            border-radius: 6px;
            transition: background 0.3s ease;
            color: var(--text-light);
            font-size: 1em;
        }

        input:focus {
            background: #e0e0e0;
            outline: none;
        }

        .submit-btn {
            width: 100%;
            padding: 12px;
            background-color: var(--color1);
            border: none;
            color: #ffffff;
            font-size: 16px;
            border-radius: 6px;
            cursor: pointer;
            transition: all 0.3s ease;
            position: relative;
            overflow: hidden;
        }

        .submit-btn::before {
            content: '';
            position: absolute;
            top: 0;
            left: -100%;
            width: 100%;
            height: 100%;
            background: linear-gradient(90deg, transparent, rgba(255, 255, 255, 0.3), transparent);
            transition: left 0.5s ease;
        }

        .submit-btn:hover::before {
            left: 100%;
        }

        .submit-btn:hover {
            background-color: var(--color2);
            transform: translateY(-2px);
            box-shadow: 0 4px 12px rgba(0, 192, 96, 0.3);
        }

        .signup-message {
            color: var(--text-light);
        }

        .signup-link {
            margin-top: 20px;
            text-align: center;
            font-size: 14px;
        }

        .signup-link a {
            color: var(--color1);
            text-decoration: none;
        }

        .signup-link a:hover {
            color: var(--color2);
            text-decoration: underline;
        }

        .error {
            color: var(--color3);
            text-align: center;
            margin-bottom: 15px;
            font-size: 0.9em;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>Login</h2>
        <% String error = (String) request.getAttribute("error"); %>
        <% if (error != null) { %>
            <div class="error"><%= error %></div>
        <% } %>
        <form action="login" method="post">
            <div class="form-group">
                <label for="email">Email Address</label>
                <input type="text" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" required>
            </div>
            <input type="submit" class="submit-btn" value="Login">
             <center>
           </center> 
        </form>
        <div class="signup-link">
            <p class="signup-message">Don't have an account? <a href="signup.jsp">Sign Up</a></p>
        </div>
    </div>
</body>
</html>