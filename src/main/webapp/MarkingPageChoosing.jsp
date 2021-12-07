<%-- 
    Document   : MarkingPageChoosing
    Created on : Nov 14, 2021, 5:08:12 AM
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
<body>
<div style="font-family: Garamond, serif;color: black;font-size: 80px">
    <label>Student Submissions</label>
    <hr color="darksalmon" size="5" noshade>
</div>
<%
    int groupId = (int) request.getAttribute("groupId");
    int testId = (int) request.getAttribute("testId");
    int[] students = (int[]) request.getAttribute("students");
    for (int i = 0; i < students.length; i++) {
        Object name = request.getAttribute("student" + i + "name");
        if (name != null) {
%>
<form action="GroupPageServlet" method="Post" style="font-family: Garamond, serif;color: black">
    <div style="width: 50%;  float: left; text-align: right">
        <input type="hidden" id="groupId" name="groupId" value=<%=groupId%>>
        <input type="hidden" id="testId" name="testId" value=<%=testId%>>
        <input type="hidden" id="studentId" name="studentId" value=<%=students[i]%>>
        <label><%=name%>
        </label>
    </div>
    <div style="width: 50%;  float: left; text-align: left">
        <input type="submit" name="act" value="choose">
    </div>
</form>
<%
        }
    }
%>
</body>
</html>
