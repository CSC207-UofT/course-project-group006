<%-- 
    Document   : regester
    Created on : Nov 11, 2021, 3:57:58 AM
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
        <form action="regesterServlet" method="Post">
            <label for="username">username
            <input type="text" name="username" id="username"></br>
            <label for="password">password
            <input type="text" name="password" id="password"></br>
            <label for="email">email
            <input type="text" name="email" id="email"></br>
            <label> regester as</label>
            <input type="submit" name="act" id="act" value="teacher">
            <input type="submit" name="act" id="act" value="student">
        </form>
    </body>
</html>
