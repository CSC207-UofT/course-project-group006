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
            <label>Choose a due date:</label><br>
            <label>year</label>
            <input type="number" name="year" id="year"><br>
            <label>month</label>
            <input type="number" name="month" id="month"><br>
            <label>day</label>
            <input type="number" name="day" id="day"><br>
            <label>hour</label>
            <input type="number" name="hour" id="hour"><br>
            <label>minuet</label>
            <input type="number" name="minuit" id="minuit"><br>
            <input type="submit" name ="act" id="act" value="finsh">
        </form>
    </body>
</html>
