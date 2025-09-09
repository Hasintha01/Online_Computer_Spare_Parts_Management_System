<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="model.User" %>

<!DOCTYPE html>
<html>
<head>
    <title>Product Catalog</title>
    <c:url var="styleUrl" value="/css/style.css" />
    <link rel="stylesheet" href="${styleUrl}" />
    <style>
        /* Theme base */
        body {
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
            color: #333;
            margin: 0;
            padding: 0 20px 40px;
        }
        /* Hero Section */
        .hero {
            max-width: 900px;
            margin: 40px auto 60px;
            text-align: center;
        }
        .hero img {
            max-width: 100%;
            height: auto;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        }
        .hero h1 {
            margin-top: 20px;
            font-size: 2.8em;
            color: #333;
        }
        .hero p {
            font-size: 1.2em;
            color: #717171;
            margin-top: 10px;
        }

        /* Carousel Container */
        .carousel-container {
            max-width: 960px;
            margin: 0 auto;
            position: relative;
            display: flex;
            align-items: center;
        }

        /* Navigation Buttons */
        .nav-btn {
            background-color: #007bff;
            border: none;
            color: white;
            font-size: 2em;
            padding: 10px 15px;
            cursor: pointer;
            border-radius: 6px;
            user-select: none;
            transition: background-color 0.3s ease;
        }
        .nav-btn:disabled {
            background-color: #a0c8ff;
            cursor: not-allowed;
        }
        .nav-btn:hover:not(:disabled) {
            background-color: #0056b3;
        }

        /* Product Boxes Wrapper */
        .product-boxes {
            display: flex;
            gap: 20px;
            overflow: hidden;
            flex-grow: 1;
            margin: 0 15px;
        }

        /* Individual Product Box */
        .product-box {
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 10px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.05);
            flex: 0 0 calc((100% / 3) - 13.33px); /* 3 boxes with gap */
            display: flex;
            flex-direction: column;
            position: relative;
            padding: 15px;
            box-sizing: border-box;
            min-height: 420px; /* increased height to accommodate button */
        }

        /* Stock badge top-left */
        .stock-badge {
            position: absolute;
            top: 12px;
            left: 12px;
            background-color: #ff5e78;
            color: white;
            font-weight: bold;
            font-size: 0.85em;
            padding: 5px 10px;
            border-radius: 6px;
            box-shadow: 0 2px 6px rgba(0,0,0,0.15);
        }

        /* Product Image */
        .product-image {
            width: 100%;
            height: 180px;
            object-fit: contain;
            border-radius: 8px;
            margin-bottom: 15px;
            background-color: #f0f0f0;
        }

        /* Product Name */
        .product-name {
            font-size: 1.3em;
            font-weight: bold;
            margin: 0 0 10px 0;
            color: #333;
        }

        /* Product Description */
        .product-desc {
            flex-grow: 1;
            color: #717171;
            font-size: 0.95em;
            margin-bottom: 15px;
            overflow: hidden;
            text-overflow: ellipsis;
            display: -webkit-box;
            -webkit-line-clamp: 3; /* limit lines */
            -webkit-box-orient: vertical;
        }

        /* Product Price */
        .product-price {
            font-weight: bold;
            color: #ff5e78;
            font-size: 1.2em;
            margin-bottom: 15px;
        }

    .btnRq {
    position: fixed;     
    top: 75px;           
        right: 15px;       
    padding: 8px 20px;
    background-color: #a10623;
    color: white;
    text-decoration: none;
    border-radius: 10px;
    font-weight: 600;
    font-size: 14px;
    cursor: pointer;
    box-shadow: 0 3px 6px rgba(255, 56, 92, 0.4);
    transition: background-color 0.3s ease;
    z-index: 1000;      
}

    .btnRp {
    position: fixed;     
    top: 135px;           
        right: 15px;       
    padding: 8px 20px;
    background-color: #a10623;
    color: white;
    text-decoration: none;
    border-radius: 10px;
    font-weight: 600;
    font-size: 14px;
    cursor: pointer;
    box-shadow: 0 3px 6px rgba(255, 56, 92, 0.4);
    transition: background-color 0.3s ease;
    z-index: 1000;      
}

.btnUser{
    position: fixed;     
    top: 15px;           
        right: 15px;       
    padding: 8px 20px;
    background-color: #a10623;
    color: white;
    text-decoration: none;
    border-radius: 10px;
    font-weight: 600;
    font-size: 14px;
    cursor: pointer;
    box-shadow: 0 3px 6px rgba(255, 56, 92, 0.4);
    transition: background-color 0.3s ease;
    z-index: 1000;
}
        /* Add to Cart Button (used for View Details now) */
        .add-to-cart-btn {
            background-color: #ff5e78;
            color: white;
            border: none;
            padding: 12px 15px;
            border-radius: 6px;
            cursor: pointer;
            font-weight: bold;
            font-size: 1em;
            width: 100%;
            transition: background-color 0.3s ease;
            align-self: stretch;
            margin-top: auto;
        }
        .add-to-cart-btn:hover {
            background-color: #e04e65;
        }

        /* Responsive */
        @media (max-width: 800px) {
            .product-box {
                flex: 0 0 100%;
            }
            .carousel-container {
                flex-direction: column;
            }
            .nav-btn {
                font-size: 1.5em;
                margin: 10px 0;
            }
            .product-boxes {
                margin: 20px 0;
            }
        }
        
        
    </style>
</head>
<body>

<div class="hero">
<%
    User user = (User) session.getAttribute("user");
    if (user != null) {
        String username = user.getFirstName(); 
        int user_id = user.getUserId();
%>
   <% %> <img src="<%= request.getContextPath() %>/images/Hero_Image.jpeg" alt="Hero Image" />
    <h1>Welcome to Our Exclusive Product Catalog</h1>
	<a href="<%= request.getContextPath() %>/account?userId=<%= user.getUserId() %>" class="btnUser">Logged as <%= user.getFirstName() %></a>	
    <a href="${pageContext.request.contextPath}/Request.jsp" class="btnRq">Request us</a>
		<a href="${pageContext.request.contextPath}/RqListById?user_id=<%= user_id %>" class="btnRp">Responds for you</a>
    <% 
    }
    %>
    <p>Explore our top products handpicked just for you!</p>
</div>

<div class="carousel-container">
    <button class="nav-btn" id="prevBtn" aria-label="Previous Products">&lt;</button>
    <div class="product-boxes" id="productBoxes">
        <c:forEach var="product" items="${products}" varStatus="status">
            <div class="product-box" data-index="${status.index}" style="display:none;">
                <div class="stock-badge">Remaining units: ${product.stock}</div>
                <img src="${product.imageUrl}" alt="${product.name}" class="product-image" />
                <h2 class="product-name">${product.name}</h2>
                <p class="product-desc">${product.description}</p>
                <p class="product-price">$${product.price}</p>

                <a href="${pageContext.request.contextPath}/productDetails?productId=${product.productId}" 
                   class="add-to-cart-btn" role="button">Add to Cart</a>
            </div>
        </c:forEach>
    </div>
    <button class="nav-btn" id="nextBtn" aria-label="Next Products">&gt;</button>

</div>

<script>
    (function() {
        const boxes = document.querySelectorAll('.product-box');
        const total = boxes.length;
        const visibleCount = 3;
        let startIndex = 0;

        const prevBtn = document.getElementById('prevBtn');
        const nextBtn = document.getElementById('nextBtn');

        function updateCarousel() {
            // Hide all boxes
            boxes.forEach(box => box.style.display = 'none');

            // Show 3 boxes starting from startIndex
            for (let i = 0; i < visibleCount; i++) {
                let idx = (startIndex + i) % total;
                boxes[idx].style.display = 'flex';
            }
        }

        prevBtn.addEventListener('click', () => {
            startIndex = (startIndex - visibleCount + total) % total;
            updateCarousel();
        });

        nextBtn.addEventListener('click', () => {
            startIndex = (startIndex + visibleCount) % total;
            updateCarousel();
        });

        // Initialize
        if(total > 0) {
            updateCarousel();
        } else {
            document.querySelector('.carousel-container').innerHTML = '<p style="text-align:center; color:#721c24; background:#f8d7da; padding:15px; border-radius:6px;">No products available.</p>';
        }
    })();
</script>

</body>
</html>
