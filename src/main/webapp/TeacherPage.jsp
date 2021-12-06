<%@ page import="java.util.List" %>
<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div style="width: 50%; position: fixed;left: 40%">
            <% List<Integer> groups =(List<Integer>)request.getAttribute("groups");
                String teacherName = request.getAttribute("teacherName").toString();
            %>
            <%= teacherName%> 's page<br>
            groups<br>
            <!--out.print(teacherManager.getNameById(userId)+"'s page:<br>groups:<br>");-->
            <% for (Integer group : groups) {%>
            <form action="TeacherPageServlet" method="Post" onsubmit= "return conform()" style="font-size: xxx-large">
                <label><%=request.getAttribute("group" + group + "name")%>
                </label>
                <input type="hidden" name="groupId" id="groupId" value=<%=group%>>
                <input type="submit" name="act" id="act" value="deleat">
                <input type="submit" name="act" id="act" value="detal">
            </form>
            <br>
            <!--out.print("<form action=\"TeacherPageServlet\" method=\"Post\">");
            out.print("<label \">"+groupManager.getName(groups.get(i)));
            out.print("<input type=\"hidden\" name=\"groupId\" id=\"groupId\" value="+groups.get(i)+">");
            out.print("<input type=\"submit\" name=\"act\" id=\"act\" value=\"deleat\">");
            out.print("<input type=\"submit\" name=\"act\" id=\"act\" value=\"detal\">");
            out.print("</form></br>");-->
            <%}%>
            <form action="TeacherPageServlet" method="Post" name="creatGroup" id = "creatGroup">
            <input type="text" name="testName" id="testName">
            <input type="submit" name="act" id="act" value="Creat">
            </form><br>
            <form action="TeacherPageServlet" method="Post">
            <input type="submit" name="act" id="act" value="Tests">
            </form><br>
        </div>
    <script>
        function conform(){
            if(document.activeElement.value ==="deleat") {
                return confirm("do you really want to deleat this?")
            }
            return true
        }
    </script>
    </body>
</html>
