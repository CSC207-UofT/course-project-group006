<%-- 
    Document   : TestSubmiation
    Created on : Nov 14, 2021, 3:17:19 AM
    Author     : darcy
--%>

<%@page import="QuestionTypes.QuestionInterface" %>
<%@page import="java.util.List" %>
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
<body style="font-family: Garamond, serif;color: black">
<div style="font-family: Garamond, serif;color: black;font-size: 80px">
    <label>Student's Test</label>
    <hr color="darksalmon" size="5" noshade>
</div>
<%
    List<QuestionInterface> questions = (List<QuestionInterface>) request.getAttribute("questions");
    String[] question = (String[]) request.getAttribute("question");
    String[] answer = (String[]) request.getAttribute("answer");
    int[] mark = (int[]) request.getAttribute("mark");
    int testId = (int) request.getAttribute("testId");
    int studentId = (int) request.getAttribute("studentId");
    int groupId = (int) request.getAttribute("groupId");
    if (request.getAttribute("answers") != null) {
        String[] answers = (String[]) request.getAttribute("answers");
%>
<form action="TestSubmissionServlet" method="Post">
    <input type="hidden" name="testId" id="testId" value=<%=testId%>>
    <input type="hidden" name="studentId" id="studentId" value=<%=studentId%>>
    <input type="hidden" name="groupId" id="groupId" value=<%=groupId%>>
    <input type="hidden" name="testSize" id="testSize" value=<%=answers.length %>>
    <%
        for (int i = 0; i < answers.length; i++) {%>
    <label>
        Question:<%=question[i] %>
    </label>
    <br>
    <label>
        Sample Answer:<%=answer[i] %>
    </label>
    <br>
    <label>
        Student's Answer:<%=answers[i] %>
    </label>
    <br>
    <input type="number" name=<%="Q"+i+"grade"%> id=<%="Q"+i+"grade"%>><label>
    <%="/" + mark[i] %>
</label>
    <br>
    <%
        }
    %>
    <hr>
    <input type="submit" id="act" name="act" value="grade">
</form>
} else {%>

<label>this student did not hand in any thing yet</label>
<form action="TestSubmissionServlet" method="Post">
    <input type="hidden" name="groupId" id="groupId" value=<%=(int)request.getAttribute("groupId")%>>
    <input type="submit" name="act" value="back">
</form>
<%}%>
</body>
</html>
