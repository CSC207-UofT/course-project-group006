package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Arrays;
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
        // System.out.println("aksytbfcawlf:   "+request.getParameter("groupId"));
        int groupId = Integer.parseInt(request.getParameter("groupId"));
        request.setAttribute("groupId", request.getParameter("groupId"));
        request.setAttribute("groupName", groupManager.getNameById(groupId));
        request.setAttribute("teacherName",teacherManager.getNameById(groupManager.getTeacher(groupId)));
        int[] students = groupManager.getStudents(groupId);
        request.setAttribute("students", students);
        request.setAttribute("posts",groupManager.posts(groupId));
        int userId = getUserId(request);
        if(userId==-1){
            try{
                response.sendRedirect("LogInPage.html");

            }catch(IOException ignored){

            }
        }else{
            //String type = teacherManager.getUserType(userId);
            for (int student : students) {
                if (student != 0) {
                    request.setAttribute("student" + student + "name", studentManager.getNameById(student));
                }
            }
        }
        String type =getUserType(request);
        //if(studentManager.IsStudent(userId)){
          //  type="S";
        //}else if(teacherManager.IsTeacher(userId)){
          //  type="T";
        //}
        //System.out.println(type);
        request.setAttribute("userType", type);
        request.setAttribute("userId", userId);
        request.setAttribute("Tests",groupManager.getTests(groupId));
        for(Integer i:groupManager.getTests(groupId).keySet()){
            request.setAttribute("Tests"+i+"name",testManager.getTestName(i));
            if(Objects.equals(type, "S")){
                int[] markgot = groupManager.getMarks(groupId,i,userId);
                //System.out.println(Arrays.toString(markgot));
                if(markgot!=null) {
                    int[] totalMark = testManager.getMarks(i);
                    int total = 0;
                    int got = 0;
                    for (int j : totalMark) {
                        total += j;
                    }
                    for (int j : markgot) {
                        got += j;
                    }
                    request.setAttribute("test" + i + "total", total);
                    request.setAttribute("test" + i + "got", got);
                }
            }
        }

        RequestDispatcher r = request.getRequestDispatcher("GroupDetail.jsp");
        r.forward(request, response);
    }
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest1(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
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
     * Build page for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @param out servlet print writer
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

            }catch(IOException ignored){

            }
        }else{
            String type = teacherManager.getUserType(userId);
            for (int student : students) {
                out.print("<form action=\"GroupPageServlet\" method=\"Post\">");
                out.print("<label \">" + studentManager.getNameById(student));
                out.print("<input type=\"hidden\" name=\"studentId\" id=\"studentId\" value=" + student + ">");
                out.print("<input type=\"hidden\" name=\"groupId\" id=\"groupId\" value=" + id + ">");
                if (type.equals("T")) {
                    out.print("<input type=\"submit\" name=\"act\" id=\"act\" value=\"delete\">");
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
     * Delete for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     */
    public void delete(HttpServletRequest request, HttpServletResponse response){
        System.out.println("studentId:"+request.getParameter("studentId")+"groupId:"+request.getParameter("groupId"));
        groupManager.removeStudentFromGroup(Integer.parseInt(request.getParameter("studentId")), Integer.parseInt(request.getParameter("groupId")));
        try{
            response.sendRedirect("GroupPageServlet?groupId="+request.getParameter("groupId"));
        }catch(IOException ignored){
            // Do nothing
        }
    }

    /**
     * Create title
     * @param id the ID
     * @return The String title
     */
    public String createTitle(int id){
        return groupManager.getName(id)+" Teacher: " + teacherManager.getNameById(groupManager.getTeacher(id)) +" ";
    }

    /**
     * Assign in test page
     *
     * @param request servlet request
     * @param response servlet response
     * @throws IOException if an I/O error occurs
     */
    public void assign(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("TestPageServlet?groupId="+request.getParameter("groupId"));
    }

    /**
     * Finish section
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void finish(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int groupId = Integer.parseInt(request.getParameter("groupId"));
        int testId = Integer.parseInt(request.getParameter("testId"));
        int year = Integer.parseInt(request.getParameter("year"));
        int month = Integer.parseInt(request.getParameter("month"));
        int day = Integer.parseInt(request.getParameter("day"));
        int hour = Integer.parseInt(request.getParameter("hour"));
        int minute = Integer.parseInt(request.getParameter("minute"));
        LocalDateTime due= LocalDateTime.of(year,month,day,hour,minute);
        //System.out.println(due);
        groupManager.addTest(groupId, testId, due);
        processRequest(request,response);
    }
    /**
     * Starter
     *
     * @param request servlet request
     * @param response servlet response
     * @throws IOException if an I/O error occurs
     */
    public void start(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendRedirect("TestPresenterServlet?testId="+request.getParameter("testId")+
                "&groupId="+request.getParameter("groupId"));
    }
    /**
     * Start marking
     *
     * @param request servlet request
     * @param response servlet response
     * @throws IOException if an I/O error occurs
     */
    public void startMarking(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendRedirect("MarkingPageChoosing.jsp");
    }

    /**
     * Give the grade
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
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
     * Send redirect to TestSubmissionServlet
     *
     * @param request servlet request
     * @param response servlet response
     * @throws IOException if an I/O error occurs
     */
    public void choose(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendRedirect("TestSubmissionServlet?testId="+request.getParameter("testId")+
                "&groupId="+request.getParameter("groupId")+
                "&studentId="+request.getParameter("studentId"));

    }
    /**
     * Process back to student or teacher page, send redirect to TeacherPageServlet or StudentPageServlet
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void back(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int user=getUserId(request);
        if(Objects.equals(getUserType(request), "T")){
            response.sendRedirect("TeacherPageServlet");
        }else if(Objects.equals(getUserType(request), "S")){
            response.sendRedirect("StudentPageServlet");
        }
    }
    /**
     * Remove test by groupManager
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void removeTest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int testId=Integer.parseInt(request.getParameter("testId"));
        int groupId=Integer.parseInt(request.getParameter("groupId"));
        groupManager.removeTest(groupId,testId);
        processRequest(request,response);
    }
    /**
     * Post announcement
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void postAnnouncement(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        groupManager.postAnnouncement(Integer.parseInt(request.getParameter("groupId")),request.getParameter("announcement"));
        processRequest(request,response);
    }
}
