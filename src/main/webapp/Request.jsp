<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Request</title>
<style type="text/css">
.form-container {
    background: white;
    padding: 30px;
    border-radius: 15px;
    box-shadow: 0 4px 12px rgba(0,0,0,0.1);
    max-width: 600px;
    margin: 40px auto;
}

.form-title {
    text-align: center;
    color: #ff385c;
    font-size: 24px;
    margin-bottom: 20px;
}

.form-group {
    display: flex;
    flex-direction: column;
    margin-bottom: 15px;
}

.form-group label {
    margin-bottom: 5px;
    color: #333;
    font-weight: bold;
}

.form-group input[type="text"],
.form-group textarea,
.form-group input[type="file"] {
    padding: 10px;
    border-radius: 10px;
    border: 1px solid #ccc;
    font-size: 14px;
}

textarea {
    resize: vertical;
    min-height: 100px;
}

</style>
</head>
<body>

<div class="main-content">
    <div class="form-container">
        <h2 class="form-title">Submit Request</h2>

        <form action="<%= request.getContextPath() %>/AddRequest" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="user_id">User ID</label>
                <input type="text" id="user_id" name="user_id" required>
            </div>

            <div class="form-group">
                <label for="Req_title">Request Title</label>
                <input type="text" id="Req_title" name="Req_title" required>
            </div>

            <div class="form-group">
                <label for="Req_description">Request Description</label>
                <textarea id="Req_description" name="Req_description" rows="4" required></textarea>
            </div>

            <div class="form-group">
                <label for="images">Attach Images (max 3)</label>
                <input type="file" name="images" id="images" multiple accept="image/*" onchange="limitImages(this)">
            </div>

            <button type="submit" class="btn">Submit Request</button>
        </form>
    </div>
</div>


<script>

function limitImages(input){
    if(input.files.length>3){
        alert("You can only upload a maximum of 3 images.");
        input.value = ""; // Clear the input
    }
}
</script>

</body>
</html>