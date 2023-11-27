<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="model.MainMenu"%>

<% MainMenu mainMenu=new MainMenu(); %>

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
   <h1>Add New Order</h1>

   <form class="form-container" action="/order/add/submit" method="POST">
      <div class="form-row">
         <label for="name">Name:</label>
         <input type="text" id="name" name="name" placeholder="Enter name" required>
      </div>

      <div class="form-row">
         <label for="order">Order:</label>
         <select id="order" name="order" required>
            <% for (Map.Entry<String, Double> entry : mainMenu.getItemPrices().entrySet()) { %>
               <option value="<%= entry.getKey() %>"><%= entry.getKey() %> - ₱<%=entry.getValue()%></option> 
            <% } %>
         </select>
      </div>

      <div class="form-row">
         <label for="qty">Quantity:</label>
         <div class="number-input">
            <button  type="button" onclick="this.parentNode.querySelector('input[type=number]').stepDown()"></button>
            <input value="1" id="qty" step="1" name="qty" min="1" type="number" placeholder="Enter quantiy">
            <button  type="button" onclick="this.parentNode.querySelector('input[type=number]').stepUp()" class="plus"></button>
         </div>
      </div>
      <div class="form-buttons">
         <button class="button" type="button" onclick="location.href='/order'">Back to Home</button>
         <button class="button" type="submit">Submit Order</button>
      </div>
   </form>
</body>

   <style>
      <%@ include file="/styles/style.css" %>
   </style>
</html>