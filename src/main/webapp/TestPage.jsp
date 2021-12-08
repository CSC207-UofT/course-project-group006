<%-- 
    Document   : TespPage
    Created on : Nov 12, 2021, 6:20:13 AM
    Author     : darcy
--%>

<%@page import="java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
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
<body style="font-family: Garamond, serif">
<label style="color: black;font-size: 80px"><%=request.getAttribute("userName")%>'s
    Tests</label>
<hr color="darksalmon" size="5" noshade>
<div style="padding: 5px">
    <%
        ArrayList<Integer> ownedTest = (ArrayList<Integer>) request.getAttribute("ownedTest");
        for (Integer i : ownedTest) {
    %>
    <form action="TestPageServlet" method="Post">
        <label><%=request.getAttribute("test" + i)%>
        </label>
        <input type="hidden" name="testId" id="testId" value=<%=i%>>
        <input type="submit" name="act" id="act" value="detail">
        <%
            if (request.getParameter("groupId") != null) {%>
        <input type="hidden" name="groupId" id="groupId" value=<%=request.getParameter("groupId")%>>
        <input type="submit" name="act" id="act" value="assign">
        <%
            }
        %>
    </form>
    <%}%>
    <br>
    <button style="background-color: darksalmon;
            border-radius: 25px;
            border: none;
            color: white;
            padding: 5px 10px;
            text-decoration: none;
            margin: auto;
            cursor: pointer;" onclick="showForm()">create new test
    </button>
    <form action="TestPageServlet" method="Post" id="createTest" hidden>
        <label for="testName"> name your test</label>
        <input type="text" name="testName" id="testName">
        <label for="timeLimit"> set a time limit for it</label>
        <input type="number" name="timeLimit" id="timeLimit">
        <input type="submit" name="act" value="add">
    </form>
</div>
<script>
    function showForm() {
        document.getElementById("createTest").hidden = !document.getElementById("createTest").hidden;
    }
</script>
<form action="TestPageServlet" method="Post" style="position: fixed; bottom: 0px">
    <input type="submit" name="act" value="back">
</form>
</body>
</html>
