<%-- 
    Document   : GroupDetail
    Created on : Nov 11, 2021, 12:50:48 AM
    Author     : darcy
--%>

<%@page import="java.time.LocalDateTime" %>
<%@page import="java.util.HashMap" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="Servlets.GroupPageServlet" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<style>

    div {
        float: left;
    }

    .left {
        width: 50%;
    }

    .right {
        width: 10%
    }

    .middle {
        width: 40%;
    }

    button {
        float: right;
    }

    form {
        width: 100%;
    }
</style>
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
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>

    </title>
</head>
<body>
<header style="font-family: Garamond, serif;color: black;font-size: 80px">
    <%=request.getAttribute("groupName") + " Instructor: " + request.getAttribute("teacherName")%>
    <button onclick="showPeople()">:</button>
</header>
<hr color="darksalmon" size="5" noshade>
<div class="left" style="font-family: Garamond, serif;">
    <label style="color: black;font-size: 25px">Tests: </label>
    <%
        HashMap<Integer, LocalDateTime> tests = (HashMap<Integer, LocalDateTime>) request.getAttribute("Tests");
        String groupId = request.getAttribute("groupId").toString();
        if (tests != null) {
            for (Integer i : tests.keySet()) {
    %>
    <form action="GroupPageServlet" method="Post">
        <label><%=request.getAttribute("Tests" + i + "name") + "    due at " + tests.get(i)%>
        </label>
        <input type="hidden" name="testId" id="testId" value=<%=i%>>
        <input type="hidden" name="groupId" id="groupId" value=<%=groupId%>>
        <%if (request.getAttribute("userType").equals("S")) {
            if(request.getAttribute("test" + i + "got")==null){
        %>
        <input type="submit" name="act" id="act" value="start">
        <%}else{%>
        <label><%=""+request.getAttribute("test" + i + "got")+"/"+request.getAttribute("test" + i + "total")%></label>
        <%
                }} else if (request.getAttribute("userType").equals("T")) {%>
        <input type="submit" name="act" id="act" value="grade">
        <%}%>
    </form>
    <%
            }
        }
    %>
    <%if (request.getAttribute("userType").equals("T")) {%>
    <form action="GroupPageServlet" method="Post">
        <input type="hidden" name="groupId" id="groupId" value=<%=groupId%>>
        <input type="submit" name="act" id="act" value="assign">
    </form>
    <%}%>
</div>
<div class="middle">
    <%
        String post =  request.getAttribute("posts").toString();%>
    <label><%=post%></label>
    <%
        if(request.getAttribute("userType").equals("T")){
    %>
    <form action="GroupPageServlet" method="post">
        <input type="hidden" name="groupId" id="groupId" value=<%=groupId%>>
        <label for="announcement">
            Enter your text for announcement
        </label><input type="text" name="announcement" id="announcement">
        <input type="submit" name="act" id="act" value="postAnnouncement">
    </form>
    <%}%>
</div>
<div class="right" id="people" hidden>
    <%
        int[] students = (int[]) request.getAttribute("students");
        for (int student : students) {
    %>
    <%
        if (student != 0) {
    %>
    <form action="GroupPageServlet" method="Post">
        <label><%= request.getAttribute("student" + student + "name")%>
        </label>
        <input type="hidden" name="studentId" id="studentId" value=<%=student%>>
        <input type="hidden" name="groupId" id="groupId" value=<%=groupId%>>
        <%if (request.getAttribute("userType").equals("T")) {%>
        <input type="submit" name="act" id="act" value="delete">
        <%}%>
    </form>
    <br>
    <%
            }
        }
    %>

</div>
<script>
    function showPeople() {
        document.getElementById("people").hidden = !document.getElementById("people").hidden;
    }
</script>
<form action="GroupPageServlet" method="Post" style="position: fixed; bottom: 0px">
    <input type="submit" name="act" value="back">
</form>
</body>
</html>
