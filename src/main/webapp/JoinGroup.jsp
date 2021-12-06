<%-- 
    Document   : JoinGroup
    Created on : Nov 11, 2021, 2:48:06 PM
    Author     : darcy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.HashMap" %>
<!DOCTYPE html>
<html>
<head>
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
<body>
<div style="font-family: Garamond, serif;color: black;font-size: 80px">
    <label>Groups</label>
    <hr color="darksalmon" size="5" noshade>
</div>
<div style="text-align: center;align-content: center">
    <%
        HashMap<Integer, String> allGroup = (HashMap<Integer, String>) request.getAttribute("allGroups");
        for (Integer i : allGroup.keySet()) {
    %>

    <form action="JoinGroupServlet" method="Post" style="padding: 5px;font-size: 25px">
        <div style="width: 50%; float: left; text-align: right">
            <label><%= allGroup.get(i)%>
            </label>
        </div>
        <div style="width: 50%; float: left; text-align: left">
            <label>:&nbsp;</label>
            <input type="hidden" name="groupId" id="groupId" value=<%=i%>>
            <input type="submit" name="act" id="act" value="join">
        </div>
    </form>
    <%}%>
</div>
<div style="padding-top: 20px;align-content: center;text-align: center">
    <form action="JoinGroupServlet" method="Post">
        <input type="submit" name="act" id="act" value="back">
    </form>
</div>
</body>
</html>
