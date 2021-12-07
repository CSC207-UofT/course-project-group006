<%-- 
    Document   : StudentPage
    Created on : Nov 11, 2021, 4:43:54 AM
    Author     : darcy
--%>

<%@page import="java.util.HashMap" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <!--<link rel="stylesheet" href="generalStyle.css">-->
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style>
        input[type=button], input[type=submit], input[type=reset] {
            background-color: darksalmon;
            border-radius: 25px;
            border: none;
            color: white;
            padding: 5px 10px;
            text-decoration: none;
            margin: auto;
            cursor: pointer;
        }
    </style>
    <title>Classroom</title>
</head>
<body style="background-color: white">
<!--<form action ="StudentPageServlet" method="Post" style="width:90%">
    <input type="text" id="word" name="word">
    <input type="submit" name="act" id="act" value="search">
</form>-->
<div style="font-family: Garamond, serif;color: black;font-size: 80px;background-color: white">
    <%
        String studentName = request.getAttribute("studentName").toString();
    %>
    <%= studentName%> 's Page
    <form action="LogInPageServlet" method="post" style="float: right; width: 10%">
        <input type="submit" name="act" id="act" value="LogOut">
    </form>
    <hr color="darksalmon" size="5" noshade>
</div>

<div style="background-color:white;font-family: Garamond, serif;color: black;text-align: center;align-content: center">
    <form action="StudentPageServlet" method="Post" style="background-color: white">
        <input type="submit" name="act" id="act" value="JoinGroup">
    </form>
    <!--<form action="StudentPageServlet" method="Post">
        <input type="submit" name="act" id="act" value="learn">
    </form>-->
</div>
<label style="font-size: 30px;background-color: white;font-family: Garamond, serif">&nbsp;Your Groups:</label>
<div style="background-color: white;padding: 5px">
    <%
        HashMap<Integer, String> joinedGroup = (HashMap<Integer, String>) request.getAttribute("joinedGroup");
        for (int i : joinedGroup.keySet()) {
    %>

    <form action="StudentPageServlet" method="Post" style="background-color: white;font-size: xxx-large">
        <div style="font-family: Garamond, serif; width: 33%; float: left; text-align: right">
            <label style="background-color: white;font-size: 25px"><%= joinedGroup.get(i)%>
            </label>
            <label style="background-color: white;font-size: 25px">:</label>
        </div>
        <div style="width: 33%; float: left;padding-left: 10px; text-align: right">
            <input style="font-family: Garamond, serif" type="hidden" name="groupId" id="groupId" value=<%=i%>>
            <input style="font-family: Garamond, serif" type="submit" name="act" id="act" value="detail">
            <label style="background-color: white">&nbsp;</label>
        </div>
        <div style="width: 33%; float: left">
            <input style="font-family: Garamond, serif" type="submit" name="act" id="act" value="quit">
        </div>
        <hr>
    </form>
    <%}%>

</div>

</body>
</html>
