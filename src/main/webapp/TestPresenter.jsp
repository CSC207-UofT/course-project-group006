<%-- 
    Document   : TestPresenter
    Created on : Nov 13, 2021, 6:03:17 PM
    Author     : darcy
--%>

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
<body style="font-family: Garamond, serif;color: black">
<div style="font-family: Garamond, serif;color: black;font-size: 80px">
    <label>Test Started</label>
    <label style="font-size: 20px">Please enter your answers to each question, good luck!</label>
    <hr color="darksalmon" size="5" noshade>
</div>

<form action="TestPresenterServlet" method="Post">
    <input type="hidden" id="groupId" name="groupId" value=<%=request.getParameter("groupId")%>>
    <input type="hidden" id="testId" name="testId" value=<%=request.getParameter("testId")%>>
    <%int testSize = (int) request.getAttribute("testSize");%>
    <input type="hidden" id="testSize" name="testSize" value=<%=testSize%>>
    <%
        for (int i = 0; i < testSize; i++) {
    %>
    <label><%=request.getAttribute("Q" + i + "question")%>
    </label><br>
    <textarea name=<%="Q"+i+"answer"%> id=<%="Q" + i + "answer"%>>
            </textarea>
    <br>
    <%}%>
    <input type="submit" name="act" id="act" value="submit">
</form>
</body>
</html>
