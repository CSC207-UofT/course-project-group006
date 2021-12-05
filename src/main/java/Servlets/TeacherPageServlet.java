/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;



import BackEnd.Gateways.StudentGateway;
import BackEnd.Gateways.TeacherGateway;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import java.util.List;

/**
 *
 * @author darcy
 */
@WebServlet(name = "TeacherPageServlet", urlPatterns = {"/TeacherPageServlet"})
public class TeacherPageServlet extends TestServlet {
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
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TestServlet</title>");
            out.println("</head>");
            out.println("<body>");
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
                List<Integer> groups=groupManager.createdBy(userId);
                out.print(teacherManager.getNameById(userId)+"'s page:<br>groups:<br>");
                for (Integer group : groups) {
                    out.print("<form action=\"TeacherPageServlet\" method=\"Post\">");
                    out.print("<label \">" + groupManager.getName(group));
                    out.print("<input type=\"hidden\" name=\"groupId\" id=\"groupId\" value=" + group + ">");
                    out.print("<input type=\"submit\" name=\"act\" id=\"act\" value=\"deleat\">");
                    out.print("<input type=\"submit\" name=\"act\" id=\"act\" value=\"detal\">");
                    out.print("</form></br>");
                }
                out.print("<form action=\"TeacherPageServlet\" method=\"Post\" name=\"creatGroup\" id = \"creatGroup\">");
                out.print("<input type=\"text\" name=\"testName\" id=\"testName\">");
                out.print("<input type=\"submit\" name=\"act\" id=\"act\" value=\"Creat\">");
                out.print("</form></br>");
                out.print("<form action=\"TeacherPageServlet\" method=\"Post\">");
                out.print("<input type=\"submit\" name=\"act\" id=\"act\" value=\"Tests\">");
                out.print("</form></br>");

            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Redirect the response to TeacherPageServlet and delete group
     * @param request Servlet request
     * @param response Servlet response
     */
    public void deleat(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("groupId"));
        groupManager.deleteGroup(id,new StudentGateway(),new TeacherGateway());
        try{
            response.sendRedirect("TeacherPageServlet");
        }catch(IOException e){
            // Do nothing
        }
    }

    /**
     * Redirect the response to GroupPageServlet
     * @param request Servlet request
     * @param response Servlet response
     */
    public void detal(HttpServletRequest request, HttpServletResponse response){
        try{
            response.sendRedirect("GroupPageServlet?groupId="+request.getParameter("groupId"));
        }catch(IOException e){
            // Do nothing
        }
    }

    /**
     * Redirect the response to TestPageServlet
     * @param request Servlet request
     * @param response Servlet response
     */
    public void Tests(HttpServletRequest request, HttpServletResponse response){
        try{
            response.sendRedirect("TestPageServlet");
        }catch(IOException e){
            // Do nothing
        }
    }

    /**
     * Redirect the response to TeacherPageServlet and create a new group
     * @param request Servlet request
     * @param response Servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void Creat(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        groupManager.createGroup(getUserId(request), request.getParameter("testName"),new TeacherGateway());
        response.sendRedirect("TeacherPageServlet");
    }

}
