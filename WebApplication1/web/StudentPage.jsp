<%-- 
    Document   : StudentPage
    Created on : Nov 11, 2021, 4:43:54 AM
    Author     : darcy
--%>

<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action ="StudentPageServlet" method="Post" style="width:90%">
            <input type="text" id="word" name="word">
            <input type="submit" name="act" id="act" value="search">
        </form>
        <div style="width: 50%; float:left">
            <%
                HashMap<Integer,String> joinedGroup =(HashMap<Integer,String>) request.getAttribute("joinedGroup");
                for(int i : joinedGroup.keySet()){
                %>
           
            <form action="StudentPageServlet" method="Post">
                <label><%= joinedGroup.get(i)%></label>
                <input type="hidden" name="groupId" id="groupId" value=<%=i%>>
                <input type="submit" name="act" id="act" value="detal">
                <input type="submit" name="act" id="act" value="quit">
            </form>
                <br>
        <%}%>
        <form action="StudentPageServlet" method ="Post">
            <input type="submit" name="act" id="act" value="JoinGroup">
        </form>
        </div>
        <div style="width: 50%; float:left">
            <form action="StudentPageServlet" method="Post">
                <input type="submit" name="act" id="act" value="learn">                
            </form>
        </div>
    </body>
</html>
