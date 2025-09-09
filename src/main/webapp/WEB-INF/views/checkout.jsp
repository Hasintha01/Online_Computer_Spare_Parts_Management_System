<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>Checkout</title>
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
            color: #333333;
            font-weight: 700;
            margin-bottom: 20px;
            text-align: center;
        }

        /* Empty cart message */
        .empty-message {
            background-color: #f8d7da; /* light pink */
            color: #721c24; /* dark red */
            border: 1px solid #f5c6cb;
            padding: 20px 25px;
            border-radius: 8px;
            max-width: 700px;
            width: 100%;
            text-align: center;
            font-weight: 600;
            box-shadow: 0 2px 8px rgba(114,28,36,0.3);
            margin-bottom: 40px;
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

        /* Order summary table */
        .order-summary-table {
            width: 100%;
            max-width: 700px;
            border-collapse: collapse;
            margin-bottom: 40px;
            background-color: #ffffff;
            border: 1px solid #dddddd;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
            overflow: hidden;
            font-size: 1em;
        }
        .order-summary-table th, .order-summary-table td {
            padding: 15px 20px;
            text-align: left;
            border-bottom: 1px solid #dddddd;
            color: #333333;
        }
        .order-summary-table th {
            background-color: #f0f0f0;
            font-weight: 700;
        }
        .order-summary-table tbody tr:hover {
            background-color: #fafafa;
        }
        .order-summary-table tfoot tr td {
            font-weight: 700;
            font-size: 1.1em;
            color: #333333;
        }
        .order-summary-table tfoot tr td[colspan="3"] {
            text-align: right;
        }
        .order-summary-table td:nth-child(3),
        .order-summary-table td:nth-child(4) {
            color: #ff5e78;
            font-weight: 700;
        }

        /* Form container */
        form {
            max-width: 700px;
            width: 100%;
            background-color: #ffffff;
            padding: 30px 25px;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
            box-sizing: border-box;
        }

        label {
            display: block;
            margin-bottom: 6px;
            font-weight: 700;
            color: #333333;
        }

        input[type="text"],
        input[type="email"],
        input[type="tel"],
        textarea,
        select {
            width: 100%;
            padding: 10px 12px;
            margin-bottom: 20px;
            border: 1px solid #dddddd;
            border-radius: 6px;
            font-size: 1em;
            font-family: inherit;
            color: #333333;
            resize: vertical;
            transition: border-color 0.3s ease;
        }
        input[type="text"]:focus,
        input[type="email"]:focus,
        input[type="tel"]:focus,
        textarea:focus,
        select:focus {
            outline: none;
            border-color: #ff5e78;
            box-shadow: 0 0 5px #ff5e78;
        }

        /* Payment method radio buttons */
        .payment-methods {
            margin-bottom: 25px;
        }
        .payment-methods label {
            font-weight: 600;
            margin-right: 20px;
            cursor: pointer;
        }
        .payment-methods input[type="radio"] {
            margin-right: 8px;
            cursor: pointer;
        }

        /* Submit button */
        button[type="submit"] {
            background-color: #ff5e78;
            color: white;
            border: none;
            padding: 14px 30px;
            border-radius: 6px;
            cursor: pointer;
            font-weight: 700;
            font-size: 1.1em;
            transition: background-color 0.3s ease, box-shadow 0.3s ease;
            width: 100%;
        }
        button[type="submit"]:hover,
        button[type="submit"]:focus-visible {
            background-color: #e04e65;
            outline: none;
            box-shadow: 0 0 8px #e04e65;
        }

        /* Responsive */
        @media (max-width: 700px) {
            body {
                padding: 30px 15px;
            }
            form, .order-summary-table {
                max-width: 100%;
            }
        }
    </style>
</head>
<body>
    <h1>Checkout</h1>

    <c:if test="${empty cartItems}">
        <div class="empty-message" role="alert">
            <p>Your cart is empty. <a href="<c:url value='/catalog' />">Continue shopping</a></p>
        </div>
    </c:if>

    <c:if test="${not empty cartItems}">
        <h2>Order Summary</h2>
        <table class="order-summary-table" role="table" aria-label="Order summary">
            <thead>
                <tr>
                    <th scope="col">Product Name</th>
                    <th scope="col">Quantity</th>
                    <th scope="col">Price</th>
                    <th scope="col">Subtotal</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${cartItems}">
                    <tr>
                        <td>${item.product.name}</td>
                        <td>${item.quantity}</td>
                        <td>$${item.product.price}</td>
                        <td>$${item.product.price * item.quantity}</td>
                    </tr>
                </c:forEach>
            </tbody>
            <tfoot>
                <tr>
                    <td colspan="3" style="text-align:right;"><strong>Total:</strong></td>
                    <td style="color:#ff5e78;">$<c:out value="${totalAmount}" /></td>
                </tr>
            </tfoot>
        </table>

        <h2>Customer Information</h2>
        <form action="<c:url value='/checkout' />" method="post" aria-label="Checkout form">
            <label for="fullName">Full Name:</label>
            <input type="text" id="fullName" name="fullName" required />

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required />

            <label for="phone">Phone Number:</label>
            <input type="tel" id="phone" name="phone" pattern="[0-9+\-\s]{7,15}" placeholder="+1 234 567 8901" required />

            <label for="altPhone">Alternate Phone Number:</label>
            <input type="tel" id="altPhone" name="altPhone" pattern="[0-9+\-\s]{7,15}" placeholder="+1 234 567 8902" />

            <label for="postalCode">Postal Code:</label>
            <input type="text" id="postalCode" name="postalCode" pattern="[A-Za-z0-9\s\-]{3,10}" placeholder="e.g. 12345 or A1B 2C3" required />

            <label for="address">Shipping Address:</label>
            <textarea id="address" name="address" rows="4" required></textarea>

            <fieldset class="payment-methods">
                <legend style="font-weight:700; margin-bottom:10px;">Payment Method:</legend>
                <label>
                    <input type="radio" name="paymentMethod" value="online" required />
                    Online Payment
                </label>
                <label>
                    <input type="radio" name="paymentMethod" value="cod" />
                    Cash on Delivery (COD)
                </label>
            </fieldset>

            <button type="submit">Place Order</button>
        </form>
    </c:if>
</body>
</html>
