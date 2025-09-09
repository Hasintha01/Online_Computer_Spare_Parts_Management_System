<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign Up - Spare Parts Management</title>
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

        .signup-container {
            background: var(--card-bg);
            padding: 40px 30px;
            border-radius: 12px;
            width: 100%;
            max-width: 400px;
            box-shadow: var(--shadow);
            animation: fadeIn 0.6s ease-in-out;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }

        .signup-container:hover {
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

        p {
            color: var(--text-light);
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
        input[type="email"],
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

        .password-toggle {
            position: relative;
        }

        .password-toggle-icon {
            position: absolute;
            right: 12px;
            top: 38px;
            cursor: pointer;
            color: #666666;
            font-size: 0.9em;
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

        .login-link {
            margin-top: 20px;
            text-align: center;
            font-size: 14px;
        }

        .login-link a {
            color: var(--color1);
            text-decoration: none;
        }

        .login-link a:hover {
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
    <script>
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

        function validateSignupForm() {
            const firstName = document.getElementById('first_name').value.trim();
            const lastName = document.getElementById('last_name').value.trim();
            const email = document.getElementById('email').value.trim();
            const password = document.getElementById('password').value;

            if (!firstName) {
                alert('First name is required.');
                return false;
            }
            if (!lastName) {
                alert('Last name is required.');
                return false;
            }
            if (!emailRegex.test(email)) {
                alert('Enter a valid email address.');
                return false;
            }
            if (password.length < 6) {
                alert('Password must be at least 6 characters.');
                return false;
            }
            return true;
        }

        function togglePassword() {
            const passwordInput = document.getElementById("password");
            const icon = document.getElementById("toggleIcon");
            if (passwordInput.type === "password") {
                passwordInput.type = "text";
                icon.textContent = "Hide";
            } else {
                passwordInput.type = "password";
                icon.textContent = "Show";
            }
        }
    </script>
</head>
<body>
    <div class="signup-container">
        <h2>Create an Account</h2>
        <% String error = (String) request.getAttribute("error"); %>
        <% if (error != null) { %>
            <div class="error"><%= error %></div>
        <% } %>
        <form action="signup" method="post" onsubmit="return validateSignupForm();">
            <div class="form-group">
                <label for="first_name">First Name</label>
                <input type="text" id="first_name" name="first_name" required>
            </div>
            <div class="form-group">
                <label for="last_name">Last Name</label>
                <input type="text" id="last_name" name="last_name" required>
            </div>
            <div class="form-group">
                <label for="email">Email Address</label>
                <input type="email" id="email" name="email" required>
            </div>
            <div class="form-group password-toggle">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" required>
                <span class="password-toggle-icon" onclick="togglePassword()" id="toggleIcon">Show</span>
            </div>
            <input type="submit" class="submit-btn" value="Sign Up">
        </form>
        <div class="login-link">
            <p>Already registered? <a href="login.jsp">Log In</a></p>
        </div>
    </div>
</body>
</html>