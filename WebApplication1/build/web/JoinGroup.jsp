<%-- 
    Document   : JoinGroup
    Created on : Nov 11, 2021, 2:48:06 PM
    Author     : darcy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.HashMap"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div style="width: 50%; float:left">
            <%
                HashMap<Integer,String> allGroup =(HashMap<Integer,String>) request.getAttribute("allGroups");
                for(Integer i : allGroup.keySet()){
                %>
           
            <form action="JoinGroupServlet" method="Post">
                <label><%= allGroup.get(i)%></label>
                <input type="hidden" name="groupId" id="groupId" value=<%=i%>>
                <input type="submit" name="act" id="act" value="join">
            </form>
                <br>
        <%}%>
    </body>
</html>
