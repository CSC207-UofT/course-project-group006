package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import BackEnd.Command;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author darcy
 */
@WebServlet(name = "GroupPageServlet", urlPatterns = {"/GroupPageServlet"})
public class GroupPageServlet extends TestServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("groupId", request.getParameter("groupId"));
        int groupId = Integer.parseInt(request.getParameter("groupId"));
        request.setAttribute("groupName", groupManager.getNameById(groupId));
        request.setAttribute("teacherName",teacherManager.getNameById(groupManager.getTeacher(groupId)));
        int[] students = groupManager.getStudents(groupId);
        request.setAttribute("students", students);
        int userId = getUserId(request);
        if(userId==-1){
            try{
                response.sendRedirect("LogInPage.html");

            }catch(IOException e){
                // Do nothing
            }
        }else{
            String type = teacherManager.getUserType(userId);
            for (int student : students) {
                if (student != 0) {
                    request.setAttribute("student" + student + "name", studentManager.getNameById(student));
                }
            }
        }
        request.setAttribute("userType", teacherManager.getUserType(userId));
        request.setAttribute("userId", userId);
        request.setAttribute("Tests",groupManager.getTests(groupId));
        for(Integer i:groupManager.getTests(groupId).keySet()){
            request.setAttribute("Tests"+i+"name",testManager.getTestName(i));
        }
        RequestDispatcher r = request.getRequestDispatcher("GroupDetail.jsp");
        r.forward(request, response);
    }

    /**
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest1(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GroupPageServlet</title>");
            out.println("</head>");
            out.println("<body>");
            buildPage( request,  response, out);
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     *
     * @param request servlet request
     * @param response servlet response
     * @param out output of the servlet
     */
    public void buildPage(HttpServletRequest request, HttpServletResponse response,PrintWriter out){
        int id = Integer.parseInt(request.getParameter("groupId"));
        int[] students = groupManager.getStudents(id);
        out.println("<h1>"+groupManager.getName(id)+" Teacher: " + teacherManager.getNameById(groupManager.getTeacher(id)) +" "+ id+"</h1>");
        Cookie[] c = request.getCookies();
        int userId=-1;
        for (Cookie cookie : c) {
            if (cookie.getName().equals("userId")) {
                userId = Integer.parseInt(cookie.getValue());
            }
        }
        if(userId==-1){
            try{
                response.sendRedirect("LogInPage.html");

            }catch(IOException e){
                //do nothing
            }
        }else{
            String type = teacherManager.getUserType(userId);
            for (int student : students) {
                out.print("<form action=\"GroupPageServlet\" method=\"Post\">");
                out.print("<label \">" + studentManager.getNameById(student));
                out.print("<input type=\"hidden\" name=\"studentId\" id=\"studentId\" value=" + student + ">");
                out.print("<input type=\"hidden\" name=\"groupId\" id=\"groupId\" value=" + id + ">");
                if (type.equals("T")) {
                    out.print("<input type=\"submit\" name=\"act\" id=\"act\" value=\"deleat\">");
                }
                out.print("</form></br>");
            }
            //out.println("<script type=\"text/javascript\">");
            //out.print("window.location='LogInPage.html';");
            //out.println("alert('wrong username or password');");
            //out.println("</script>");
        }
    }

    /**
     *
     * @param request Servlet request
     * @param response Servlet response
     */
    public void deleat(HttpServletRequest request, HttpServletResponse response){
        groupManager.removeStudentFromGroup(Integer.parseInt(request.getParameter("studentId")), Integer.parseInt(request.getParameter("groupId")));
        try{
            response.sendRedirect("GroupPageServlet?groupId="+request.getParameter("groupId"));
        }catch(IOException e){
            // Do nothing
        }
    }

    /**
     *
     * @param id Group ID
     * @return Group Name and Teacher Name
     */
    public String creatTitle(int id){
        return groupManager.getName(id)+" Teacher: " + teacherManager.getNameById(groupManager.getTeacher(id)) +" ";
    }

    /**
     *
     * @param request Servlet request
     * @param response Servlet response
     */
    public void assign(HttpServletRequest request, HttpServletResponse response){
        try{
            response.sendRedirect("TestPageServlet?groupId="+request.getParameter("groupId"));
        }catch(IOException e){
            // Do nothing
        }
    }
    /**
     *
     * @param request Servlet request
     * @param response Servlet response
     */
    public void finsh(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int groupId = Integer.parseInt(request.getParameter("groupId"));
        int testId = Integer.parseInt(request.getParameter("testId"));
        int year = Integer.parseInt(request.getParameter("year"));
        int month = Integer.parseInt(request.getParameter("month"));
        int day = Integer.parseInt(request.getParameter("day"));
        int hour = Integer.parseInt(request.getParameter("hour"));
        int minuit = Integer.parseInt(request.getParameter("minuit"));
        LocalDateTime due= LocalDateTime.of(year,month,day,hour,minuit);
        groupManager.addTest(groupId, testId, due);
        processRequest(request,response);
    }
    /**
     *
     * @param request Servlet request
     * @param response Servlet response
     */
    public void start(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("TestPresenterServlet?testId="+request.getParameter("testId")+
                "&groupId="+request.getParameter("groupId"));
    }

    /**
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void startMarking(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("MarkingPageChoosing.jsp");
    }
    /**
     *
     * @param request Servlet request
     * @param response Servlet response
     */
    public void grade(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int testId=Integer.parseInt(request.getParameter("testId"));
        request.setAttribute("testId", testId);
        int groupId=Integer.parseInt(request.getParameter("groupId"));
        request.setAttribute("groupId", groupId);
        int[] students=groupManager.getStudents(groupId);
        request.setAttribute("students", students);
        for(int i=0;i<students.length;i++){
            if(students[i]!=0){
                request.setAttribute("student"+i+"name", studentManager.getNameById(students[i]));
            }
        }
        RequestDispatcher r = request.getRequestDispatcher("MarkingPageChoosing.jsp");
        r.forward(request, response);
    }
    /**
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void choose(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("TestSubmissionServlet?testId="+request.getParameter("testId")+
                "&groupId="+request.getParameter("groupId")+
                "&studentId="+request.getParameter("studentId"));

    }
    /**
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void back(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int user=getUserId(request);
        if(Objects.equals(studentManager.getUserType(user), "T")){
            response.sendRedirect("TeacherPageServlet");
        }else if(Objects.equals(studentManager.getUserType(user), "S")){
            response.sendRedirect("StudentPageServlet");
        }
    }
}