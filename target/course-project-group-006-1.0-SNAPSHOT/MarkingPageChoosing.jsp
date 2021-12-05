<%-- 
    Document   : MarkingPageChoosing
    Created on : Nov 14, 2021, 5:08:12 AM
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
        <%
            int groupId=(int)request.getAttribute("groupId");
            int testId=(int)request.getAttribute("testId");
            int[] students=(int[])request.getAttribute("students");
            for(int i=0;i<students.length;i++){
            Object name = request.getAttribute("student"+i+"name");
            if(name!=null){
        %>
        <form action="GroupPageServlet" method="Post">
            <input type="hidden" id="groupId" name="groupId" value=<%=groupId%>>
            <input type="hidden" id="testId" name="testId" value=<%=testId%>>
            <input type="hidden" id="studentId" name="studentId" value=<%=students[i]%>>
            <label><%=name%></label>
            <input type="submit" name="act" id="act" value="choose">
        </form>
        <%}}%>
    </body>
</html>
