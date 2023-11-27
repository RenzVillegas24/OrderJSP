<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="model.MainMenu"%>

<% MainMenu mainMenu=new MainMenu(); %>
<% String type = request.getAttribute("type").toString(); %>

<!-- initiaalize a scanner -->

<!DOCTYPE html>
<html lang="en">

<head>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css">
   <title>Add Order</title>
</head>

<body>
    <h1>
        <% if (type.equals("printInfo")) { %>
            Printed Order
        <% } else if (type.equals("addSuccess")) { %>
            Order Information
        <% } %>
    </h1>
      <div class="info-container">
        <% if (type.equals("addSuccess") || type.equals("printInfo")) { %>
            <% if (type.equals("printInfo")) { %>
                <h2>Order Information</h2>
            <% } else { %>
                <h2>Order Added Successfully!</h2>     
            <% } %>

            <div class="info-row">
                <span class="info-label">Order #:</span>
                <span class="info-value"><%= request.getAttribute("id") %></span>
            </div>   
            <div class="info-row">
                <span class="info-label">Date Ordered:</span>
                <span class="info-value"><%= request.getAttribute("dateTime") %></span>
            </div>                 
            <div class="info-row">
                <span class="info-label">Customer Name:</span>
                <span class="info-value"><%= request.getAttribute("name") %></span>
            </div>
            <div class="info-row">
                <span class="info-label">Item:</span>
                <span class="info-value"><%= request.getAttribute("order") %></span>
            </div>
            <div class="info-row">
                <span class="info-label">Price:</span>
                <span class="info-value">₱<%= request.getAttribute("price") %></span>
            </div>
            <div class="info-row">
                <span class="info-label">Quantity:</span>
                <span class="info-value"><%= request.getAttribute("qty") %></span>
            </div>
            <div class="info-row">
                <span class="info-label">Order Status:</span>
                <span class="info-value"><%= request.getAttribute("isPending") %></span>
            </div>
            <div class="info-row">
                <span class="info-label">Order Total:</span>
                <span class="info-value">₱<%= request.getAttribute("total") %></span>
            </div>

            <div class="form-buttons">
                <button class="button" type="button" onclick="location.href='/order'">Back to Home</button>
                <% if (type.equals("addSuccess")) { %>
                    <button class="button" type="button" onclick="location.href='/order/add'">Add Another Order</button>
                <% } else if (type.equals("printInfo")) { %>
                    <button class="button" type="button" onclick="location.href='/order/served'">Print Another Order</button>
                <% } %>
            </div>
        <% } else if (type.equals("addFailed")) { %>
            <h2>Adding Order Failed</h2>
            
        <% } else { %>


        <% } %>

    </div>       



</body>

   <style>
      <%@ include file="/styles/style.css" %>
   </style>
</html>