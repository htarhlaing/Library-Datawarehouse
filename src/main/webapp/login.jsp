<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 11/21/2022
  Time: 4:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login Page</title>
    <style>
        h3{color: white;}
    </style>
    <link rel="stylesheet" href="css/index_login.css" />
</head>
<body>
<%
    String message=(String) request.getAttribute("message");
%>
<form action="ServletLogin" method="GET" >
    <div class="login">
        <h2>Admin Login</h2>

        <div class="login_box">

            <input type="text" name='uname' id='name' class="login_input" required="required" />
            <label for="name">Username</label>
        </div>
        <div class="login_box">

            <input type="password" name='password' id='pwd' class="login_input" required="required">
            <label for="pwd">Password</label>
        </div>
        <input type="submit" class="login_btn" value="login">

    </div>
</form>
<br>
<br>
<%
if(message!=null){
%>

<h3><%= message%></h3>
<%}%>
</body>
</html>
