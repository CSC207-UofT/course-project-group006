<%-- 
    Document   : TespPage
    Created on : Nov 12, 2021, 6:20:13 AM
    Author     : darcy
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><%=request.getAttribute("userName")%>'s tests</h1>
        <div>
            <%
                ArrayList<Integer> ownedTest = (ArrayList<Integer>)request.getAttribute("ownedTest");
                for(Integer i: ownedTest){
            %>
            <form action ="TestPageServlet" method="Post">
                <label/> <%=request.getAttribute("test"+i)%></label>
                <input type="hidden" name="testId" id="testId" value=<%=i%>>
                <input type="submit" name ="act" id="act" value="detal">
                <%
                    if(request.getParameter("groupId")!=null){%>
                    <input type="hidden" name="groupId" id="groupId" value=<%=request.getParameter("groupId")%>>
                    <input type="submit" name ="act" id="act" value="asign">
                    <%
                    }
                %>
            </form>
            <%}%>
            <button onclick="showForm()">creat new test</button>
            <form action ="TestPageServlet" method="Post" id = "creatTest"hidden>
                <label> name your test</label>
                <input type="text" name="testName" id="testName">
                <label> set a time limit for it</label>
                <input type ="number" name ="timeLimit" id="timeLimit">
                <input type="submit" name ="act" id="act" value="add">
            </form>
        </div>
            <script>
                function showForm(){
                    document.getElementById("creatTest").hidden=!document.getElementById("creatTest").hidden;
                }
            </script>
            <form action ="TestPageServlet" method="Post" style="position: fixed; bottom: 0px">
                <input type="submit" name ="act" id="act" value="back">
            </form>
    </body>
</html>
