<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Request Submitted</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f6f6f6;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .success-box {
            background-color: white;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        .success-box h1 {
            color: #28a745;
            font-size: 28px;
            margin-bottom: 25px;
        }

        .btn {
    display: inline-block;
    padding: 10px 20px;
    background-color: #007bff;
    color: white;
    text-decoration: none;
    border-radius: 8px;
    font-size: 14px;
    transition: background-color 0.2s ease;
}

.btn:hover {
    background-color: #0056b3;
}


        .btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="success-box">
        <h1>Request Submitted Successfully!</h1>

        <a href="Products" class="btn">Go to Inventory</a>
<a href="${pageContext.request.contextPath}/Request.jsp" class="btn">Submit Another Request</a>

    </div>
</body>
</html>
