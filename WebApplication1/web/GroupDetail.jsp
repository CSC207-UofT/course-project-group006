<%-- 
    Document   : GroupDetail
    Created on : Nov 11, 2021, 12:50:48 AM
    Author     : darcy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@ page import= "Servlets.GroupPageServlet" %>
<!DOCTYPE html>
<html>
    <style>
        
        div{
            float: left;
        }
        .left{
            width: 90%;
        }
        .right{
            width:10%
        }
        button{
            float: right;
        }
    </style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
            
        </title>
    </head>
    <body>
        <header>
            <%=request.getAttribute("groupName")+" "+request.getAttribute("teacherName")%>
            <button onclick="showPeople()">:</button>
        </header>
        <div class="left">
            a
        </div>
        <div class="right" id="people" hidden>
            <%
                int[] students = (int[])request.getAttribute("students");
                String groupId = request.getAttribute("groupId").toString();
                for(int i = 0; i<students.length;i++){
                %>
            <%
                    if(students[i]!=0){
            %>
            <form action="GroupPageServlet" method="Post">
                <label><%= request.getAttribute("student"+students[i]+"name")%></label>
                <input type="hidden" name="studentId" id="studentId" value=<%=students[i]%>>
                <input type="hidden" name="groupId" id="groupId" value=<%=groupId%>>
                <%if(request.getAttribute("userType").equals("T")){%>
                <input type="submit" name="act" id="act" value="delete">
                <%}%>
            </form>
                <br>
            <%}
}%>
            
        </div>
<script>
    function showPeople(){
        document.getElementById("people").hidden=!document.getElementById("people").hidden;
    }
</script>
    </body>
</html>
