<%-- 
    Document   : TestSubmiation
    Created on : Nov 14, 2021, 3:17:19 AM
    Author     : darcy
--%>

<%@page import="QuestionTypes.QuestionInterface"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            List<QuestionInterface> questions = (List<QuestionInterface>)request.getAttribute("questions");
            int testId = (int)request.getAttribute("testId");
            int studentId = (int)request.getAttribute("studentId");
            int groupId = (int)request.getAttribute("groupId");
            if(request.getAttribute("answers")!=null){
            String[] answers = (String[])request.getAttribute("answers");
        %>
        <form action="TestSubmitionServlet" method="Post">
            <input type ="hidden" name="testId" id="testId" value=<%=testId%>>
            <input type ="hidden" name="studentId" id="studentId" value=<%=studentId%>>
            <input type ="hidden" name="groupId" id="groupId" value=<%=groupId%>>
            <input type ="hidden" name="testSize" id="testSize" value=<%=answers.length %>>
            <%
                for(int i=0;i<answers.length;i++){%>
                <label>
                    Question:<%=questions.get(i).getQuestion() %>
                </label>
                <br>
                <label>
                    Sample Answer:<%=questions.get(i).getAnswer() %>
                </label>
                <br>
                <label>
                    Student's Answer:<%=answers[i] %>
                </label>
                <br>
                    <input type="number"  name=<%="Q"+i+"grade"%> id=<%="Q"+i+"grade"%>>
                <%}
            %>
            <input type="submit" id="act" name="act" value="grade">
        </form>
        <%}else{%>
        
         <label>this student did not hand in any thing yet</label>
        <form action="TestSubmitionServlet" method="Post">
            <input type="submit" id="act" name="act" value="back">
        </form>
        <%}%>
    </body>
</html>
