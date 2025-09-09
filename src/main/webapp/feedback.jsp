<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.User, java.util.List, model.Feedback, java.util.*" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Vaas Computers</title>
    <style>
        :root {
            --color1: #ff55e0;
            --color2: #a65bcb;
            --color3: #ffb347; /* Bright amber */
            --bgColor: #f5f5f5; /* Light gray background */
            --card-bg: #ffffff; /* White card background */
            --text-light: #333333; /* Dark gray text for readability */
            --shadow: 0 8px 24px rgba(0, 0, 0, 0.1); /* Subtle shadow */
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

        /* Navbar */
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
            text-transform: uppercase;
            letter-spacing: 1px;
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

        /* Feedback Container */
        .feedback_container {
            max-width: 900px;
            margin: 40px auto;
            padding: 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
            gap: 40px;
        }

        .heading {
            position: sticky;
            top: 60px;
            background: linear-gradient(to right, var(--color1), var(--color2));
            color: #ffffff;
            width: 100%;
            text-align: center;
            padding: 15px;
            font-size: 2em;
            font-weight: 700;
            letter-spacing: 1px;
            box-shadow: var(--shadow);
            z-index: 999;
            transform: translateY(-10px);
            animation: slideIn 0.5s ease forwards;
        }

        @keyframes slideIn {
            from { transform: translateY(-50px); opacity: 0; }
            to { transform: translateY(-10px); opacity: 1; }
        }

        /* Star Ratings Card */
        .starRatings {
            background: var(--card-bg);
            padding: 30px;
            border-radius: 16px;
            box-shadow: var(--shadow);
            width: 100%;
            max-width: 400px;
            transform: translateY(0);
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }

        .starRatings:hover {
            transform: translateY(-5px);
            box-shadow: 0 12px 32px rgba(0, 0, 0, 0.15);
        }

        .fiveStars, .feedbackPack {
            margin-bottom: 20px;
            padding: 15px;
            border-radius: 12px;
            background: #f9f9f9;
        }

        p {
            font-size: 1.2em;
            font-weight: 600;
            color: var(--text-light);
            margin-bottom: 15px;
        }

        .stars {
            display: flex;
            justify-content: center;
            gap: 10px;
        }

        .starbutton1, .starbutton2, .starbutton3, .starbutton4, .starbutton5 {
            border: none;
            background: #e0e0e0;
            color: #333333;
            width: 50px;
            height: 50px;
            font-size: 1.5em;
            cursor: pointer;
            border-radius: 50%;
            transition: all 0.3s ease;
            position: relative;
            overflow: hidden;
        }

        .starbutton1:hover { background: red; box-shadow: 0 0 15px rgba(255, 0, 0, 0.5); }
        .starbutton2:hover { background: orange; box-shadow: 0 0 15px rgba(255, 165, 0, 0.5); }
        .starbutton3:hover { background: yellow; box-shadow: 0 0 15px rgba(255, 255, 0, 0.5); }
        .starbutton4:hover { background: lightgreen; box-shadow: 0 0 15px rgba(144, 238, 144, 0.5); }
        .starbutton5:hover { background: green; box-shadow: 0 0 15px rgba(0, 128, 0, 0.5); }

        .starbutton1.selected, .starbutton1.active { background: red; box-shadow: 0 0 15px rgba(255, 0, 0, 0.5); }
        .starbutton2.selected, .starbutton2.active { background: orange; box-shadow: 0 0 15px rgba(255, 165, 0, 0.5); }
        .starbutton3.selected, .starbutton3.active { background: yellow; box-shadow: 0 0 15px rgba(255, 255, 0, 0.5); }
        .starbutton4.selected, .starbutton4.active { background: lightgreen; box-shadow: 0 0 15px rgba(144, 238, 144, 0.5); }
        .starbutton5.selected, .starbutton5.active { background: green; box-shadow: 0 0 15px rgba(0, 128, 0, 0.5); }

        .starbutton1::before, .starbutton2::before, .starbutton3::before, .starbutton4::before, .starbutton5::before {
            content: '★';
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%) scale(0);
            transition: transform 0.3s ease;
        }

        .starbutton1:hover::before, .starbutton2:hover::before, .starbutton3:hover::before, .starbutton4:hover::before, .starbutton5:hover::before,
        .starbutton1.selected::before, .starbutton2.selected::before, .starbutton3.selected::before, .starbutton4.selected::before, .starbutton5.selected::before {
            transform: translate(-50%, -50%) scale(1);
        }

        textarea {
            width: 100%;
            padding: 12px;
            border: none;
            border-radius: 8px;
            background: #f0f0f0;
            color: var(--text-light);
            resize: none;
            font-size: 1em;
            transition: background 0.3s ease;
        }

        textarea:focus {
            background: #e0e0e0;
            outline: none;
        }

        .submitButton {
            width: 100%;
            background: var(--color1);
            color: #ffffff;
            border: none;
            padding: 12px;
            font-size: 1.1em;
            font-weight: 600;
            cursor: pointer;
            margin-top: 15px;
            border-radius: 8px;
            position: relative;
            overflow: hidden;
            transition: all 0.3s ease;
        }

        .submitButton::before {
            content: '';
            position: absolute;
            top: 0;
            left: -100%;
            width: 100%;
            height: 100%;
            background: linear-gradient(90deg, transparent, rgba(255, 255, 255, 0.3), transparent);
            transition: left 0.5s ease;
        }

        .submitButton:hover::before {
            left: 100%;
        }

        .submitButton:hover {
            background: var(--color2);
            transform: translateY(-2px);
            box-shadow: 0 4px 12px rgba(0, 192, 96, 0.3);
        }

        /* Feedback List */
        .feedback-list {
            width: 100%;
            max-width: 400px;
        }

        .feedback-item {
            background: var(--card-bg);
            padding: 20px;
            border-radius: 12px;
            box-shadow: var(--shadow);
            margin-bottom: 20px;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }

        .feedback-item:hover {
            transform: translateY(-5px);
            box-shadow: 0 12px 32px rgba(0, 0, 0, 0.15);
        }

        .feedback-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 10px;
        }

        .feedback-stars {
            color: #ffd700;
            font-size: 1.2em;
        }

        .feedback-actions {
            display: flex;
            gap: 10px;
        }

        .action-button {
            padding: 8px 16px;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            color: #ffffff;
            font-size: 0.9em;
            font-weight: 600;
            transition: all 0.3s ease;
        }

        .edit-button {
            background: var(--color2);
        }

        .edit-button:hover {
            background: #00a050;
            transform: translateY(-2px);
        }

        .delete-button {
            background: var(--color3);
        }

        .delete-button:hover {
            background: #e55a5a;
            transform: translateY(-2px);
        }

        .edit-form {
            background: #f9f9f9;
            padding: 20px;
            border-radius: 12px;
            margin-top: 20px;
        }

        /* Responsive Design */
        @media (max-width: 768px) {
            .container-fluid {
                flex-direction: column;
                gap: 10px;
            }

            .nav-links {
                flex-wrap: wrap;
                justify-content: center;
            }

            .starRatings, .feedback-list {
                width: 90%;
            }

            .heading {
                font-size: 1.5em;
                padding: 10px;
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
                <a class="nav-link active" href="feedback">Feedback</a>
                <a class="nav-link" href="account">My profile</a>
                <span class="navbar-text">Welcome, <%= user.getFirstName() %> <%= user.getLastName() %></span>
                <a class="nav-link" href="logout">Logout</a>
            </div>
        </div>
    </nav>

    <div class="heading">Feedback</div>

    <div class="feedback_container">
        <div class="starRatings">
            <% if (request.getAttribute("editFeedback") == null) { %>
                <!-- Add Feedback Form -->
                <form action="feedback" method="post">
                    <input type="hidden" name="action" value="add">
                    <div class="fiveStars">
                        <p>Rate your experience with our website...</p>
                        <div class="stars">
                            <button type="button" class="starbutton1" data-value="1">1</button>
                            <button type="button" class="starbutton2" data-value="2">2</button>
                            <button type="button" class="starbutton3" data-value="3">3</button>
                            <button type="button" class="starbutton4" data-value="4">4</button>
                            <button type="button" class="starbutton5" data-value="5">5</button>
                        </div>
                        <input type="hidden" name="no_of_stars" id="no_of_stars" value="0">
                    </div>
                    <div class="feedbackPack">
                        <p>Anything that can be improved?</p>
                        <textarea rows="8" name="description" placeholder="Your feedback"></textarea>
                        <button class="submitButton" type="submit">Submit</button>
                    </div>
                </form>
            <% } else { %>
                <!-- Edit Feedback Form -->
                <%
                    Feedback editFeedback = (Feedback) request.getAttribute("editFeedback");
                %>
                <form action="feedback" method="post" class="edit-form">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="feedback_id" value="<%= editFeedback.getFeedbackId() %>">
                    <div class="fiveStars">
                        <p>Update your rating...</p>
                        <div class="stars">
                            <button type="button" class="starbutton1 <%= editFeedback.getNoOfStars() == 1 ? "active" : "" %>" data-value="1">1</button>
                            <button type="button" class="starbutton2 <%= editFeedback.getNoOfStars() == 2 ? "active" : "" %>" data-value="2">2</button>
                            <button type="button" class="starbutton3 <%= editFeedback.getNoOfStars() == 3 ? "active" : "" %>" data-value="3">3</button>
                            <button type="button" class="starbutton4 <%= editFeedback.getNoOfStars() == 4 ? "active" : "" %>" data-value="4">4</button>
                            <button type="button" class="starbutton5 <%= editFeedback.getNoOfStars() == 5 ? "active" : "" %>" data-value="5">5</button>
                        </div>
                        <input type="hidden" name="no_of_stars" id="no_of_stars" value="<%= editFeedback.getNoOfStars() %>">
                    </div>
                    <div class="feedbackPack">
                        <p>Update your feedback...</p>
                        <textarea rows="8" name="description"><%= editFeedback.getDescription() != null ? editFeedback.getDescription() : "" %></textarea>
                        <button class="submitButton" type="submit">Update</button>
                        <button class="submitButton" type="button" onclick="window.location.href='feedback'">Cancel</button>
                    </div>
                </form>
            <% } %>
        </div>

        <!-- Display Feedbacks -->
        <div class="feedback-list">
            <% 
                @SuppressWarnings("unchecked")
                List<Feedback> feedbacks = (List<Feedback>) request.getAttribute("feedbacks");
                if (feedbacks != null && !feedbacks.isEmpty()) {
                    for (Feedback feedback : feedbacks) {
            %>
                <div class="feedback-item">
                    <div class="feedback-header">
                        <span>Username: <%= feedback.getUsername() %></span>
                        <span class="feedback-stars">
                            <% for (int i = 0; i < feedback.getNoOfStars(); i++) { %>
                                ★
                            <% } %>
                        </span>
                    </div>
                    <p><%= feedback.getDescription() != null ? feedback.getDescription() : "No description" %></p>
                    <small>Posted on: <%= feedback.getCreatedAt() %></small>
                    <% if (feedback.getUserId() == user.getUserId()) { %>
                        <div class="feedback-actions">
                            <form action="feedback" method="get" style="display:inline;">
                                <input type="hidden" name="edit_id" value="<%= feedback.getFeedbackId() %>">
                                <button type="submit" class="action-button edit-button">Edit</button>
                            </form>
                            <form action="feedback" method="post" style="display:inline;">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="feedback_id" value="<%= feedback.getFeedbackId() %>">
                                <button type="submit" class="action-button delete-button">Delete</button>
                            </form>
                        </div>
                    <% } %>
                </div>
            <% 
                    }
                } else {
            %>
                <p style="color: var(--text-light); text-align: center;">No feedback available.</p>
            <% } %>
        </div>
    </div>

    <script>
        document.querySelectorAll(".stars button").forEach(button => {
            button.addEventListener("click", function() {
                document.querySelectorAll(".stars button").forEach(btn => btn.classList.remove("selected", "active"));
                this.classList.add("selected");
                document.getElementById("no_of_stars").value = this.getAttribute("data-value");
            });
        });
    </script>
</body>
</html>