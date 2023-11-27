<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="model.*"%>

<!DOCTYPE html>
<html lang="en">

<% MainMenu mainMenu=new MainMenu(); %>

<head>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css">
   <title>Pending Orders</title>
</head>

<body>
   <h1>Pending Orders</h1>

   <div class="orders-container">
        <form action="/order/pending/serve" method="post">
            <table>
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Order</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Total</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>  
                     <% for (Orders orders : mainMenu.getPendingOrders()) { %> 
                        <tr>
                            <td><%= orders.getName() %></td>
                            <td><%= orders.getOrder() %></td>
                            <td>₱<%= orders.getPrice() %></td>
                            <td><%= orders.getQuantity() %></td>
                            <td>₱<%= orders.getTotal() %></td>
                            <td>
                                <button type="submit" class="button" name="id" value="<%= orders.getId() %>" onclick="serveOrder(2)">Serve</button>
                            </td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        </form>
      <button class="button" onclick="location.href='/order'">Back to Home</button>
   </div>
   

   <script>
      function serveOrder(orderId) {
         // Handle serving a specific order (replace with your logic)
         console.log(`Order ${orderId} served.`);
      }

   </script>
</body>

   <style>
      <%@ include file="/styles/style.css" %>
   </style>
</html>