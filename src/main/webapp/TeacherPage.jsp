<%@ page import="java.util.List" %>
<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<html>
<head>
    <title>Classroom</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
</head>
<body>
<div style="font-family: Garamond, serif;color: black;font-size: 80px">
    <% List<Integer> groups = (List<Integer>) request.getAttribute("groups");
        String teacherName = request.getAttribute("teacherName").toString();
    %>
    <%= teacherName%> 's Page
    <hr color="darksalmon" size="5" noshade>
    <div style="text-align: center;align-content: center">
        <form action="TeacherPageServlet" method="Post">
            <input type="submit" name="act" id="act" value="Tests">
        </form>
    </div>

    <form action="TeacherPageServlet" method="Post" name="creatGroup" id="creatGroup">
        <label style="font-family: Garamond, serif;color: black;font-size: 25px;">New Group Name:</label>
        <input type="text" name="testName" id="testName">
        <input type="submit" name="act" id="act" value="Creat">
    </form>

    <label style="font-size: 30px;">&nbsp;Your Groups:</label>

    <!--out.print(teacherManager.getNameById(userId)+"'s page:<br>groups:<br>");-->
    <% for (Integer group : groups) {%>
    <form action="TeacherPageServlet" method="Post" onsubmit="return conform()"
          style="font-size: xxx-large;padding: 5px">
        <div style="width: 33%; float: left; text-align: right">
            <label style="font-family: Garamond, serif;color: black;font-size: 25px;"><%=request.getAttribute("group" + group + "name")%>
                <label>:</label>
        </div>
        <div style="width: 33%; float: left;padding-left: 10px; text-align: right">
            <input style="font-family: Garamond, serif" type="submit" name="act" id="act" value="detal">
            <label>&nbsp;</label>
        </div>
        <div style="width: 33%; float: left">
            <input style="font-family: Garamond, serif" type="submit" name="act" id="act" value="deleat">
        </div>

        <input style="font-family: Garamond, serif" type="hidden" name="groupId" id="groupId" value=<%=group%>>
        <hr>
    </form>
    <!--out.print("<form action=\"TeacherPageServlet\" method=\"Post\">");
    out.print("<label \">"+groupManager.getName(groups.get(i)));
    out.print("<input type=\"hidden\" name=\"groupId\" id=\"groupId\" value="+groups.get(i)+">");
    out.print("<input type=\"submit\" name=\"act\" id=\"act\" value=\"deleat\">");
    out.print("<input type=\"submit\" name=\"act\" id=\"act\" value=\"detal\">");
    out.print("</form></br>");-->
    <%}%>

</div>
<script>
    function conform() {
        if (document.activeElement.value === "deleat") {
            return confirm("do you really want to deleat this?")
        }
        return true
    }
</script>
</body>
</html>
