<%-- 
    Document   : TestPresenter
    Created on : Nov 13, 2021, 6:03:17 PM
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
        <form action="TestPresenterServlet" method="Post">
            <input type="hidden" id="geoupId" name="groupId" value=<%=request.getParameter("groupId")%>>
            <input type="hidden" id="testId" name="testId" value=<%=request.getParameter("testId")%>>
            <%int testSize = (int)request.getAttribute("testSize");%>
            <input type="hidden" id="testSize" name="testSize" value=<%=testSize%>>
            <%
            for(int i=0;i<testSize;i++){
            %>
            <label><%=request.getAttribute("Q"+i+"question")%></label><br>
            <textarea name=<%="Q"+i+"answer"%> id=<%="Q"+i+"answer"%>>
            </textarea>
            <br>
            <%}%>
            <input type="submit" name="act" id="act" value="submit">
        </form>
    </body>
</html>
