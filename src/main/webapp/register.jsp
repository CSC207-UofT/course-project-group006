<%-- 
    Document   : register
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
        <form  style="position: fixed; left: 40%;bottom:40%; background-color: darksalmon; padding: 30px; border-radius: 25px" action="RegisterServlet" method="Post" onsubmit="return checkLegal()">
            <label for="username">username</label>
            <input type="text" name="username" id="username"></br>
            <label for="password">password</label>
            <input type="text" name="password" id="password"></br>
            <label for="email">email</label>
            <input type="text" name="email" id="email"></br>
            <label> register as</label>
            <input type="submit" name="act" id="act" value="teacher">
            <input type="submit" name="act" id="act" value="student">
        </form>
        <script>
            function checkLegal(){
                if((document.getElementById("username").value===""||
                    document.getElementById("password").value===""||
                    document.getElementById("email").value==="")){
                    alert("?")
                    return false;
                }
                return true;
            }
        </script>
    </body>
</html>
