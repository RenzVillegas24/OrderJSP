<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <title>Home Page</title>
</head>

<body>
   <h1>Welcome to Our Ordering System</h1>

   <button class='button' onclick="location.href='/order/add'">ORDER NOW!</button>
   <button class='button' onclick="location.href='/order/pending'">PENDING ORDERS</button>
   <button class='button' onclick="location.href='/order/served'">SERVED ORDERS</button>
</body>

   <style>
      <%@ include file="/styles/style.css" %>
   </style>
</html>