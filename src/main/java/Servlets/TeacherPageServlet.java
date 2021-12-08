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
import javax.servlet.RequestDispatcher;
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

            int userId=getUserId(request);
            if(userId==-1){
                    response.sendRedirect("LogInPage.html");
            }else{
                List<Integer> groups=teacherManager.groups(userId);

                request.setAttribute("groups",groups);
                for (Integer group : groups) {
                    request.setAttribute("group" + group + "name", groupManager.getName(group));
                }
                request.setAttribute("teacherName",teacherManager.getNameById(userId));
                RequestDispatcher r =request.getRequestDispatcher("TeacherPage.jsp");
                r.forward(request,response);
            }

        }

    /**
     * Redirect to TeacherPageServlet and delete the specific group
     *
     * @param request servlet request
     * @param response servlet response
     */
    public void delete(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("groupId"));
        groupManager.deleteGroup(id,new StudentGateway(),new TeacherGateway());
        try{
            response.sendRedirect("TeacherPageServlet");
        }catch(IOException ignored){
        }
    }
    /**
     * Redirect to GroupPageServlet for detail
     *
     * @param request servlet request
     * @param response servlet response
     */
    public void detail(HttpServletRequest request, HttpServletResponse response){
        try{
            response.sendRedirect("GroupPageServlet?groupId="+request.getParameter("groupId"));
        }catch(IOException ignored){

        }
    }
    /**
     * Redirect to TestPageServlet for tests
     *
     * @param request servlet request
     * @param response servlet response
     */
    public void Tests(HttpServletRequest request, HttpServletResponse response){
        try{
            response.sendRedirect("TestPageServlet");
        }catch(IOException ignored){

        }
    }
    /**
     * Redirect to TeacherPageServlet to create groups
     *
     * @param request servlet request
     * @param response servlet response
     * @throws IOException if an I/O error occurs
     */
    public void Create(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        groupManager.createGroup(getUserId(request), request.getParameter("testName"),new TeacherGateway());
        response.sendRedirect("TeacherPageServlet");
    }

}
