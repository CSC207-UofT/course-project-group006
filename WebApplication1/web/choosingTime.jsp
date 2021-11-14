<%-- 
    Document   : choosingTime
    Created on : Nov 13, 2021, 4:02:41 AM
    Author     : darcy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action=<%="GroupPageServlet?groupId="+request.getParameter("groupId")+"&testId="+request.getParameter("testId") %> method="Post">
            <input type="text" name="year" id="year">
            <input type="text" name="month" id="month">
            <input type="text" name="day" id="day">
            <input type="text" name="hour" id="hour">
            <input type="text" name="minuit" id="minuit">
            <input type="submit" name ="act" id="act" value="finsh">
        </form>
    </body>
</html>
