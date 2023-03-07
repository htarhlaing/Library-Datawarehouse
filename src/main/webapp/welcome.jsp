<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 12/20/2022
  Time: 7:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/home.css">
    <title>Home</title>
    <style>
        .sub_section {
            width: 600px;
            margin: 25px auto 0;
            background-color: rgba(181, 189, 231, 0.5);
            height: 100px;
        text-align: center;
            padding-top:35px;
        }
        h3{
            color: #717171;

        }

    </style>
</head>
<body>
<%
    if(session.getAttribute("username")==null)
    {
        response.sendRedirect("login.jsp");

    }
%>

<div class="wrapper" id="wrapper">
    <div class="sidebar">
        <h2>About Books</h2>
        <ul>
            <li><a href="ServletBook?action=query1"><i class="fas"></i>what is the total number of books borrowed for each year?</a></li>
            <li><a href="query2.jsp"><i class="fas"></i>what is the total number of books borrowed per quarter in a specific year?</a></li>
            <li><a href="query3.jsp"><i class="fas"></i>what is the total number of books borrowed for each month of specific year?</a></li>
        </ul>
        <h2>About Fines</h2>
        <ul>
            <li ><a href="ServletFine?action=query4"><i class="fas"></i>what is the total amount of fine for each year?</a></li>
            <li><a href="query5.jsp"><i class="fas"></i>what is the total amount of fines per quarter in a specific year?</a></li>
            <li><a href="query6.jsp"><i class="fas"></i>what is the total amount of fine for each month of specific year?</a></li>
        </ul>
        <h2>About Students</h2>
        <ul>
            <li><a href="query7.jsp"><i class="fas"></i>what is the total number of books borrowed by a specific student per month in a specific year? </a></li>
            <li><a href="query8.jsp"><i class="fas"></i>what is the total amount of fines per month for a specific student in a specific year? </a></li>
            <li><a href="ServletStudent?action=query9"><i class="fas"></i>what are the total number of books borrowed by males and females each year? </a></li>
            <li><a href="ServletStudent?action=query10"><i class="fas"></i>what are the total fines for males and females each year? </a></li>

            <div class="logOut">
                <form action="ServletLogout" method="post">
                    <input type="submit" value="Logout" class="loginOut_btn">
                </form>
            </div>
        </ul>


    </div>
    <div class="main_content">
        <div class="header">
            Welcome to Library Data Warehouse

        </div>

        <div class="sub_section">
            <h3>Select one of the decision-making queries for the library </h3>
        </div>

    </div>
</div>

<br>

</body>
</html>

