<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>Your Shopping Cart</title>
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

        h1 {
            font-weight: 700;
            color: #333333;
            margin-bottom: 30px;
            text-align: center;
        }

        /* Empty cart message */
        .empty-message {
            background-color: #f8d7da; /* light pink */
            color: #721c24; /* dark red */
            border: 1px solid #f5c6cb;
            padding: 20px 25px;
            border-radius: 8px;
            max-width: 600px;
            width: 100%;
            text-align: center;
            font-weight: 600;
            box-shadow: 0 2px 8px rgba(114,28,36,0.3);
        }

        .empty-message p {
            margin: 10px 0;
        }

        .empty-message a {
            color: #ff5e78;
            font-weight: 700;
            text-decoration: none;
            transition: text-decoration 0.3s ease;
        }
        .empty-message a:hover,
        .empty-message a:focus-visible {
            text-decoration: underline;
            outline: none;
        }

        /* Cart table styling */
        .cart-table {
            border-collapse: collapse;
            width: 100%;
            max-width: 900px;
            background-color: #ffffff;
            border: 1px solid #dddddd;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
            overflow: hidden;
            font-size: 1em;
        }

        .cart-table th, .cart-table td {
            padding: 15px 20px;
            text-align: left;
            border-bottom: 1px solid #dddddd;
            color: #333333;
        }

        .cart-table th {
            background-color: #f0f0f0;
            font-weight: 700;
        }

        .cart-table tbody tr:hover {
            background-color: #fafafa;
        }

        /* Inputs inside table */
        .cart-table input[type="number"] {
            width: 70px;
            padding: 6px 8px;
            border: 1px solid #dddddd;
            border-radius: 6px;
            font-family: inherit;
            font-size: 1em;
            color: #333333;
            transition: border-color 0.3s ease;
        }
        .cart-table input[type="number"]:focus {
            outline: none;
            border-color: #ff5e78;
            box-shadow: 0 0 5px #ff5e78;
        }

        /* Buttons */
        button[type="submit"],
        button[type="button"] {
            background-color: #ff5e78;
            color: white;
            border: none;
            padding: 12px 25px;
            border-radius: 6px;
            cursor: pointer;
            font-weight: 700;
            font-size: 1em;
            margin: 15px 10px 0 0;
            transition: background-color 0.3s ease, box-shadow 0.3s ease;
        }
        button[type="submit"]:hover,
        button[type="submit"]:focus-visible,
        button[type="button"]:hover,
        button[type="button"]:focus-visible {
            background-color: #e04e65;
            outline: none;
            box-shadow: 0 0 8px #e04e65;
        }

        /* Remove button inside table */
        .cart-table button[name^="remove_"] {
            padding: 8px 15px;
            font-weight: 600;
            font-size: 0.9em;
        }

        /* Footer row styling */
        tfoot tr td {
            font-weight: 700;
            font-size: 1.1em;
            color: #333333;
        }
        tfoot tr td[colspan="3"] {
            text-align: right;
        }

        /* Form container */
        form {
            max-width: 900px;
            width: 100%;
            display: flex;
            flex-direction: column;
            align-items: flex-start;
        }

        /* Checkout link styling */
        a {
            text-decoration: none;
        }
        a button {
            width: auto;
        }

        /* Responsive */
        @media (max-width: 700px) {
            .cart-table th, .cart-table td {
                padding: 10px 8px;
                font-size: 0.9em;
            }
            button[type="submit"],
            button[type="button"] {
                width: 100%;
                margin: 10px 0 0 0;
            }
            form {
                align-items: center;
            }
        }
    </style>
</head>
<body>
    <h1>Your Shopping Cart</h1>

    <c:set var="cartItems" value="${cart.items}" />

    <c:choose>
        <c:when test="${empty cartItems}">
            <div class="empty-message" role="alert">
                <p>Your cart is empty.</p>
                <p><a href="<c:url value='/catalog' />">Continue Shopping</a></p>
            </div>
        </c:when>
        <c:otherwise>
            <form action="<c:url value='/cart' />" method="post" aria-label="Shopping cart form">
                <table class="cart-table" role="table" aria-label="Shopping cart items">
                    <thead>
                        <tr>
                            <th scope="col">Product Name</th>
                            <th scope="col">Price</th>
                            <th scope="col">Quantity</th>
                            <th scope="col">Subtotal</th>
                            <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${cartItems}">
                            <tr>
                                <td>${item.product.name}</td>
                                <td>$${item.product.price}</td>
                                <td>
                                    <input type="number" name="quantities" value="${item.quantity}" min="1" max="${item.product.stock}" aria-label="Quantity for ${item.product.name}" />
                                    <input type="hidden" name="productIds" value="${item.product.productId}" />
                                </td>
                                <td>$${item.product.price * item.quantity}</td>
                                <td>
                                    <button type="submit" name="action" value="remove_${item.product.productId}" aria-label="Remove ${item.product.name} from cart">Remove</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="3" style="text-align:right;"><strong>Total:</strong></td>
                            <td colspan="2">$<c:out value="${cart.totalPrice}" /></td>
                        </tr>
                    </tfoot>
                </table>
                <div>
                    <button type="submit" name="action" value="update">Update Cart</button>
                    <a href="<c:url value='/checkout' />" aria-label="Proceed to checkout">
                        <button type="button">Proceed to Checkout</button>
                    </a>
                </div>
            </form>
        </c:otherwise>
    </c:choose>
</body>
</html>
