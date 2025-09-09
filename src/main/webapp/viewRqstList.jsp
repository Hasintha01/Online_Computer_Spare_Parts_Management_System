<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, Admin.model.Request" %>
<%@ page import="Admin.model.RequestWithImages" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All requests</title>

<style type="text/css">
body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
    background-color: #f4f4f4; /* dirty white */
}

header {
    background-color: #333333; /* dark gray */
    color: white;
    padding: 1rem;
    text-align: center;
}

.container {
    margin: 2rem auto;
    max-width: 1200px;
    padding: 1.5rem;
    background: #ffffff;
    border-radius: 12px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08); /* soft gray shadow */
}

table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 1.5rem;
}

table th,
table td {
    padding: 0.75rem 1rem;
    text-align: left;
    border: 1px solid #ddd; /* light gray border */
}

table th {
    background-color: #666666; /* mid-gray header */
    color: #ffffff;
}

table tr:nth-child(even) {
    background-color: #f9f9f9; /* dirty white for zebra rows */
}

.btn {
    padding: 0.5rem 1rem;
    color: white;
    background-color: #666666;
    border: none;
    border-radius: 6px;
    cursor: pointer;
    text-decoration: none;
    font-weight: 500;
    transition: background-color 0.3s ease;
}

.btn:hover {
    background-color: #444444; /* darker gray hover */
}

.request-form {
    margin-top: 2rem;
    padding: 1rem 1.5rem;
    background: #ffffff;
    border-radius: 10px;
    box-shadow: 0 2px 6px rgba(0, 0, 0, 0.05);
}

.request-form h2 {
    margin-bottom: 1rem;
    color: #333333;
    font-size: 1.6rem;
}

.request-form label {
    font-weight: 600;
    margin-top: 1rem;
    display: block;
    color: #333333;
}

.form-input,
.form-textarea {
    width: 100%;
    padding: 0.6rem;
    margin-top: 0.4rem;
    margin-bottom: 1rem;
    border: 1px solid #ccc;
    border-radius: 4px;
    font-size: 1rem;
    background-color: #fcfcfc;
    transition: border-color 0.3s ease, box-shadow 0.3s ease;
}

.form-input:focus,
.form-textarea:focus {
    border-color: #888888;
    outline: none;
    box-shadow: 0 0 6px rgba(0, 0, 0, 0.1);
}

.popup {
    display: none;
    position: fixed;
    top: 50%;
    left: 50%;
    border-radius: 16px;
    transform: translate(-50%, -50%);
    max-height: 80vh;
    overflow-y: auto;
    background: #ffffff;
    padding: 2rem;
    box-shadow: 0 6px 20px rgba(0, 0, 0, 0.1);
    z-index: 1000;
}

.popup-overlay {
    display: none;
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(100, 100, 100, 0.2); /* gray overlay */
    z-index: 999;
}

.request-details {
    background: #f4f4f4; /* dirty white */
    border-radius: 12px;
    padding: 1.5rem 2rem;
    margin-bottom: 1.5rem;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
    max-width: 500px;
}

.request-details h2 {
    color: #333333;
    margin-bottom: 1rem;
    font-size: 1.5rem;
}

.request-details p {
    margin: 0.5rem 0;
    font-size: 1.05rem;
    color: #555555;
}

.request-details strong {
    color: #2e2e2e;
    min-width: 110px;
    display: inline-block;
}

.request-details span {
    color: #666666;
}
</style>


</head>
<body>

<header>
    <h1>Request List Dashboard</h1>
</header>

<table>
    <tr>
        <th>Request ID</th>
        <th>User ID</th>
        <th>Title</th>
        <th>Description</th>
        <th>Status</th>
        <th>Date</th>
        <th>Action</th>
    </tr>
    <%
        List<Request> list = (List<Request>) request.getAttribute("rqstListAtr");
        if (list != null) {
            for (Request r : list) {
    %>
    <tr>
        <td><%= r.getReq_id() %></td>
        <td><%= r.getUser_id() %></td>
        <td><%= r.getReq_title() %></td>
        <td><%= r.getReq_description() %></td>
        <td><%= r.getReq_status() %></td>
        <td><%= r.getReq_date() %></td>
        <td><button class="btn" onclick="openRequest('<%= r.getReq_id()%>')">View</button>
        <br>
        <form action="${pageContext.request.contextPath}/DeleteRequestServlet" method="post" onsubmit="return confirm('Are you sure you want to delete this request?');">
		<input type="hidden" name="requestId" id="requestId" value="<%= r.getReq_id() %>" />
    <button type="submit" class="btn">Delete</button>
</form>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>

<div id="review-request" class="popup">
    <div id="view-rq">
        <div class="request-details">
            <h2>Request Details</h2>
            <p><strong>Request ID:</strong> <span id="detail-req-id"></span></p>
            <p><strong>User ID:</strong> <span id="detail-user-id"></span></p>
            <p><strong>Title:</strong> <span id="detail-title"></span></p>
            <p><strong>Description:</strong> <span id="detail-description"></span></p>
            <p><strong>Status:</strong> <span id="detail-status"></span></p>
            <p><strong>Date:</strong> <span id="detail-date"></span></p>
            <div id="images-container"></div>
        </div>
    </div>

    <div id="response" class="request-form pop-up">
        <form action="responseServlet" method="POST">
            <input type="hidden" id="hidden-req-id" name="rq_id">
            <h2>Response to request</h2>
            <label for="Req_description">Response</label><br>
            <textarea id="rsp_description" name="rsp_description" rows="4" required class="form-textarea"></textarea>
            <br>
            <button type="submit" class="btn" id="btn2" onclick="hidePopup()">Send Response</button>
        </form>
    </div>

    <br>
    <button class="btn" id="btn1" style="display: block;" onclick="openReply()">Respond to Request</button>
</div>

<script>
    function openRequest(reqID) {
        document.getElementById('review-request').style.display = 'block';
        document.getElementById('response').style.display = 'none';

        fetch('RqWithImagesServlet?req_id=' + encodeURIComponent(reqID))
            .then(response => response.json())
            .then(data => {
                document.getElementById('detail-req-id').textContent = data.req_id;
                document.getElementById('detail-user-id').textContent = data.user_id;
                document.getElementById('detail-title').textContent = data.req_title;
                document.getElementById('detail-description').textContent = data.req_description;
                document.getElementById('detail-status').textContent = data.req_status;
                document.getElementById('detail-date').textContent = data.req_date;

                document.getElementById('hidden-req-id').value = data.req_id;

                let imagesDiv = document.getElementById('images-container');
                imagesDiv.innerHTML = '';
                data.imageIds.forEach(id => {
                    let img = document.createElement('img');
                    img.src = 'RqWithImagesServlet?img_id_parameter=' + id;
                    img.style.maxWidth = '300px';
                    img.style.maxHeight = '300px';
                    img.style.margin = '10px';
                    imagesDiv.appendChild(img);
                });
            })
            .catch(err => console.error('Error fetching request details:', err));
    }

    function openReply() {
        const responseDiv = document.getElementById('response');
        const btn1 = document.getElementById('btn1');
        if (responseDiv.style.display === 'none') {
            responseDiv.style.display = 'block';
            btn1.style.display = 'none';
        }
    }

    function hidePopup() {
        document.getElementById('review-request').style.display = 'none';
        document.getElementById('response').style.display = 'none';
        document.getElementById('btn1').style.display = 'block';
    }
</script>

</body>
</html>
