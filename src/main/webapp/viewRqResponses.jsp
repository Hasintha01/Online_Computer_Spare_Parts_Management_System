<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="model.RequestResponse"  %>
    <%@ page import="java.util.*"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Responses for request</title>
    <style>
        body {
            font-family: 'Segoe UI', Arial, sans-serif;
            background: #f7f9fb;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 700px;
            margin: 40px auto;
            background: #fff;
            border-radius: 10px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.07);
            padding: 32px 24px;
        }
        h1 {
            font-size: 2rem;
            margin-bottom: 24px;
            color: #2d3a4b;
            text-align: center;
        }
        .request-info {
            background: #f1f5fa;
            border-radius: 7px;
            padding: 18px 16px;
            margin-bottom: 28px;
            border-left: 4px solid #1a73e8;
        }
        .request-date {
            color: #7a869a;
            font-size: 0.98em;
            margin-bottom: 6px;
        }
        .request-description {
            color: #2d3a4b;
            font-size: 1.08em;
        }
        .responses-list {
            list-style: none;
            padding: 0;
            margin: 0;
        }
        .response-item {
            border-bottom: 1px solid #e3e7ed;
            padding: 18px 0;
            display: flex;
            flex-direction: column;
            gap: 6px;
        }
        .response-item:last-child {
            border-bottom: none;
        }
        .response-header {
            display: flex;
            justify-content: flex-end;
            align-items: center;
        }
        .response-date {
            font-size: 0.95em;
            color: #7a869a;
        }
        .response-content {
            margin-top: 4px;
            color: #333;
            font-size: 1.05em;
        }
        .no-responses {
            text-align: center;
            color: #b0b8c1;
            margin: 40px 0;
            font-size: 1.1em;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Responses for Your Request</h1>
        <%
        List<RequestResponse> rspList = (List<RequestResponse>) request.getAttribute("rqstRspListAtr");
        		if(rspList!=null&& !rspList.isEmpty()){
        			RequestResponse first = rspList.get(0);
        		
        %>
        
        <div class="request-info">
            <div class="request-date">Request Date: <%= first.getRqDate() %></div>
            <div class="request-description">
                <%= first.getRqTitle() %>
            </div>
        </div>
        <ul class="responses-list">
        
        <% for(RequestResponse r : rspList) { %>
            <li class="response-item">
                <div class="response-header">
                    <span class="response-date"><%= r.getRspDate() %></span>
                </div> 
                <div class="response-content">
                    <%= r.getRspId() %> + <%= r.getRspDescription() %>
                </div>
            </li>
            
   <%
       }
   } 
    %>
    
    </div>
</body>
</html>