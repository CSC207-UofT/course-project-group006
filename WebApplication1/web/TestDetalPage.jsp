<%-- 
    Document   : TestDetalPage
    Created on : Nov 12, 2021, 10:40:26 PM
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
        <div style="float: left; width:50%">
            Questions:
            <%
                int size = (int)request.getAttribute("testSize");
                for(int i=0; i<size;i++){
                %>
           
            <form action="TestDetalServlet" method="Post">
            <input type="hidden" name="testId" id="testId" value=<%=request.getAttribute("testId")%>>
            <label><%="Q"+(i+1)+":"%></label><br>
                <label>Question:</label><br>
                <input type="hidden" name="questionNumber" id="questionNumber" value=<%=i%>>
                <lable>"<%=request.getAttribute("Q"+i+"question")%>" </lable><br>
                <label>Answer:</label><br>
                <lable>"<%=request.getAttribute("Q"+i+"answer")%>" </lable><br>
                <input type="submit" name="act" id="act" value="delet">
            </form>
                <br>
        <%}%>
        
        </div>
            <div style="float: left; width:50%">
                Add:
                <form action="TestDetalServlet" method="Post">
            <input type="hidden" name="testId" id="testId" value=<%=request.getAttribute("testId")%>>
            <label>Question:</label><br>
            <textarea name="question" id= "question" style="width:70%" rows=10"></textarea><br>
            <label>Answer:</label><br>
            <textarea name="answer" id= "answer" style="width:70%;" rows=10></textarea><br>
            <label>Mark:</label><br>
            <input type="number" id="mark" name="mark">
                <input type="submit" name="act" id="act" value="add">
            </form>
            </div>
            <form action="TestDetalServlet" method="Post">
                <input type="submit" name="act" id="act" value="back">
            </form>
    </body>
    
</html>
