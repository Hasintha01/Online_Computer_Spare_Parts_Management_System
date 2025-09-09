<%@page import="Admin.model.Request"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
    body {
        font-family: Arial, sans-serif;
        background: #f7f7f7;
        margin: 0;
        padding: 20px;
    }
    .request-list-container {
        background: #fff;
        border-radius: 8px;
        box-shadow: 0 2px 8px rgba(0,0,0,0.07);
        max-width: 700px;
        margin: 40px auto;
        padding: 24px;
    }
    h2 {
        margin-top: 0;
        color: #333;
    }
    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 18px;
    }
    th, td {
        padding: 12px 10px;
        text-align: left;
    }
    th {
        background: #f0f0f0;
        color: #444;
        font-weight: 600;
    }
    tr:nth-child(even) {
        background: #fafafa;
    }
    .view-btn {
        background: #1976d2;
        color: #fff;
        border: none;
        border-radius: 4px;
        padding: 7px 18px;
        cursor: pointer;
        font-size: 15px;
        transition: background 0.2s;
    }
    .view-btn:hover {
        background: #125ea7;
    }
</style>


</head>
<body>

<div class="request-list-container">
    <h2>Request List</h2>
    <table>
        <thead>
            <tr>
                <th>Request ID</th>
                <th>Request title</th>
                <th>Date</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
        <%
        List<Request> list = (List<Request>) request.getAttribute("rqstListAtr");
       if(list!=null){
    	   for(Request r : list){
    		   %>
            <tr>
                <td><%= r.getReq_id() %></td>
                <td><%= r.getReq_title() %></td>
                <td><%= r.getReq_date() %></td>
                <td><%= r.getReq_status() %></td>
                <td>    <a href="<%= request.getContextPath() %>/viewRqResponses?req_id=<%= r.getReq_id() %>" class="view-btn">View</a>
</td>
            </tr>

            <% 
        
    	   } 
       }
        %>
        </tbody>
    </table>
</div>
</body>
</html>