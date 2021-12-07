<%-- 
    Document   : register
    Created on : Nov 11, 2021, 3:57:58 AM
    Author     : darcy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Classroom</title>
</head>
<body>
<label style="font-family: Garamond, serif;color: black;font-size: 80px;text-shadow: 2px 2px darksalmon;">&nbsp;Register</label><br>
<hr color="darksalmon" size="5" noshade>
<hr color="black" size="5" noshade>
<hr color="white" size="5" noshade>

<form style="width:300px;background-color: darksalmon; padding: 30px; border-radius: 25px;margin: auto"
      action="RegisterServlet" method="Post" onsubmit="return checkLegal()">
    <p>
        <label style="font-family: Garamond, serif; font-size: 30px" for="username">Username</label>
        <input type="text" name="username" id="username" style="background-color: #ccffff;">
    </p>
    <p>
        <label style="font-family: Garamond, serif; font-size: 30px" for="password">Password</label>
        <input type="text" name="password" id="password" style="background-color: #ccffff">
    <p>
        <label style="font-family: Garamond, serif; font-size: 30px" for="email">Email&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
        <input type="email" name="email" id="email" style="background-color: #ccffff">
    </p>
    <p>
        <label style="font-family: Garamond, serif; font-size: 20px"> You are a: </label>
        <input type="submit" name="act" value="teacher" style="font-family: Garamond, serif; background-color: white; float: right">
        <span style="width: 10px"></span>
        <input type="submit" name="act" value="student" style="font-family: Garamond, serif; background-color: white; float: right">
    </p>
</form>
<script>
    function checkLegal() {
        if ((document.getElementById("username").value === "" ||
            document.getElementById("password").value === "" ||
            document.getElementById("email").value === "")) {
            alert("?")
            return false;
        }
        return true;
    }
</script>
</body>
</html>
