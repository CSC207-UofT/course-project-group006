/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author darcy
 */
@WebServlet(name = "GroupPageServlet", urlPatterns = {"/GroupPageServlet"})
public class GroupPageServlet extends testServlet {

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
        request.setAttribute("groupName", userGroupManager.getNameOfGroup(groupId));
        request.setAttribute("teacherName",userManager.getName(userGroupManager.getTeacher(groupId)));
        int[] students = userGroupManager.getStudents(groupId);
        request.setAttribute("students", students);
        int userId = getUserId(request);
        if(userId==-1){
            try{
            response.sendRedirect("LogInPage.html");
            
            }catch(IOException e){
                // Do nothing
            }
        }else{
            //String type = userManager.getUserType(userId);
            for (int student : students) {
                if (student != 0) {
                    request.setAttribute("student" + student + "name", userManager.getName(student));
                }
            }
        }
        request.setAttribute("userType", userManager.getUserType(userId));
        request.setAttribute("userId", userId);
        RequestDispatcher r = request.getRequestDispatcher("GroupDetail.jsp");
        r.forward(request, response);
    }

    /**
     *
     * @param request The request from the HttpServlet
     * @param response The response from the HttpServlet
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
    public void buildPage(HttpServletRequest request, HttpServletResponse response,PrintWriter out){
        int id = Integer.parseInt(request.getParameter("groupId"));
        int[] students = userGroupManager.getStudents(id);
        out.println("<h1>"+userGroupManager.getNameOfGroup(id)+" Teacher: " + userManager.getName(userGroupManager.getTeacher(id)) +" "+ id+"</h1>");
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
                // Do nothing
            }
        }else{
            String type = userManager.getUserType(userId);
            for (int student : students) {
                out.print("<form action=\"GroupPageServlet\" method=\"Post\">");
                out.print("<label \">" + userManager.getName(student));
                out.print("<input type=\"hidden\" name=\"studentId\" id=\"studentId\" value=" + student + ">");
                out.print("<input type=\"hidden\" name=\"groupId\" id=\"groupId\" value=" + id + ">");
                if (type.equals("T")) {
                    out.print("<input type=\"submit\" name=\"act\" id=\"act\" value=\"delete\">");
                }
                out.print("</form></br>");
            }
        }
    }
    public void delete(HttpServletRequest request, HttpServletResponse response){
        userGroupManager.removeStudentFromGroup(Integer.parseInt(request.getParameter("studentId")), Integer.parseInt(request.getParameter("groupId")));
        try{
        response.sendRedirect("GroupPageServlet?groupId="+request.getParameter("groupId"));
        }catch(IOException e){
            // Do nothing
        }
    }
    public String createTitle(int id){
        return userGroupManager.getNameOfGroup(id)+" Teacher: " + userManager.getName(userGroupManager.getTeacher(id)) +" ";
    }
    
}
