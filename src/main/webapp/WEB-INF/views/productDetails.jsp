<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>Product Details</title>
    <c:url var="styleUrl" value="/css/style.css" />
    <link rel="stylesheet" href="${styleUrl}" />
    <style>
        /* Import recommended fonts */
        @import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;600&family=Roboto:wght@400;700&family=Open+Sans:wght@400;700&display=swap');

        /* Body and page layout */
        body {
            background-color: #f8f9fa; /* very light gray */
            font-family: 'Roboto', 'Inter', 'Open Sans', sans-serif;
            color: #333333; /* main text dark gray */
            margin: 0;
            min-height: 100vh;
            display: flex;
            flex-direction: column;
            align-items: center; /* center horizontally */
            padding: 40px 20px;
            -webkit-font-smoothing: antialiased;
            -moz-osx-font-smoothing: grayscale;
        }

        h1, h2 {
            color: #333333; /* dark gray for main headings */
            font-weight: 700;
            text-align: center;
        }

        /* Container for product details card */
        .product-details {
            background-color: #ffffff; /* white card background */
            border: 1px solid #dddddd; /* light gray border */
            padding: 30px 25px;
            max-width: 600px;
            width: 100%;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
            box-sizing: border-box;
            margin-top: 20px;
        }

        /* Subtext styling (e.g. IDs or less dominant text) */
        .subtext {
            color: #717171; /* medium gray */
            font-size: 0.9em;
        }

        /* Price styling */
        .price {
            color: #ff5e78; /* highlighted pink */
            font-weight: 700;
            font-size: 1.3em;
        }

        /* Error message styling */
        .error-message {
            background-color: #f8d7da; /* light pink background */
            color: #721c24; /* dark red text */
            border: 1px solid #f5c6cb;
            padding: 15px 20px;
            margin-bottom: 15px;
            border-radius: 6px;
            max-width: 600px;
            width: 100%;
            box-sizing: border-box;
            text-align: center;
            font-weight: 600;
            box-shadow: 0 2px 8px rgba(114,28,36,0.3);
        }

        /* Form styling */
        form {
            margin-top: 25px;
            display: flex;
            align-items: center;
            gap: 15px;
            flex-wrap: wrap;
        }

        label {
            font-weight: 700;
            color: #333333;
        }

        input[type="number"] {
            width: 70px;
            padding: 6px 8px;
            border: 1px solid #dddddd;
            border-radius: 6px;
            font-size: 1em;
            font-family: inherit;
            color: #333333;
            transition: border-color 0.3s ease;
        }
        input[type="number"]:focus {
            outline: none;
            border-color: #ff5e78;
            box-shadow: 0 0 5px #ff5e78;
        }

        button {
            background-color: #ff5e78;
            color: white;
            border: none;
            padding: 12px 25px;
            border-radius: 6px;
            cursor: pointer;
            font-weight: 700;
            font-size: 1em;
            transition: background-color 0.3s ease, box-shadow 0.3s ease;
            flex-shrink: 0;
        }

        button:hover,
        button:focus-visible {
            background-color: #e04e65;
            outline: none;
            box-shadow: 0 0 8px #e04e65;
        }

        /* Link styling */
        p.back-link {
            margin-top: 30px;
            text-align: center;
            width: 100%;
            max-width: 600px;
        }
        p.back-link a {
            color: #ff5e78;
            text-decoration: none;
            font-weight: 700;
            font-size: 1em;
            transition: text-decoration 0.3s ease;
        }
        p.back-link a:hover,
        p.back-link a:focus-visible {
            text-decoration: underline;
            outline: none;
        }
    </style>
</head>
<body>
    <h1>Product Details</h1>

    <c:if test="${empty product}">
        <div class="error-message" role="alert">
            <p>Product details not available.</p>
        </div>
    </c:if>

    <c:if test="${not empty product}">
        <div class="product-details" role="main" aria-label="Product details for ${product.name}">
            <h2>${product.name}</h2>
            <p><strong>Description:</strong> ${product.description}</p>
            <p><strong>Price:</strong> <span class="price">$${product.price}</span></p>
            <p><strong>Stock:</strong> ${product.stock}</p>
            <!-- Add more product attributes as needed -->

            <form action="<c:url value='/cart' />" method="post" aria-label="Add product to cart form">
                <input type="hidden" name="productId" value="${product.productId}" />
                <label for="quantity">Quantity:</label>
                <input type="number" id="quantity" name="quantity" value="1" min="1" max="${product.stock}" required />
                <button type="submit">Add to Cart</button>
            </form>
        </div>
    </c:if>

    <p class="back-link"><a href="<c:url value='/catalog' />">Back to Catalog</a></p>
</body>
</html>
