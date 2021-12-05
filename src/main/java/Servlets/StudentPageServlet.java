/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import BackEnd.Gateways.*;

/**
 *
 * @author darcy
 */
@WebServlet(name = "StudentPageServlet", urlPatterns = {"/StudentPageServlet"})
public class StudentPageServlet extends TestServlet {
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = getUserId(request);
        if(userId==-1){
            try{
                response.sendRedirect("LogInPage.html");
            }catch(IOException e){
                // Do nothing
            }
        }
        List<Integer> joinedGroup = studentManager.getJoinedGroup(userId);
        HashMap<Integer,String> gInfo = new HashMap<Integer,String>();
        for (int i:joinedGroup) {
            gInfo.put(i,groupManager.getName(i));
        }
        request.setAttribute("joinedGroup", gInfo);
        request.setAttribute("userId", userId);
        RequestDispatcher r = request.getRequestDispatcher("StudentPage.jsp");
        r.forward(request, response);
    }
    public void search(HttpServletRequest request, HttpServletResponse response) {
    }

    /**
     * Redirect the response to GroupPageServlet
     * @param request Servlet request
     * @param response Servlet response
     * @throws IOException if an I/O error occurs
     */
    public void detail(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendRedirect("GroupPageServlet?groupId="+request.getParameter("groupId"));
    }

    /**
     * Redirect the response to StudentPageServlet and remove student from group
     * @param request Servlet request
     * @param response Servlet response
     * @throws IOException if an I/O error occurs
     */
    public void quit(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        groupManager.removeStudentFromGroup(getUserId(request), Integer.parseInt(request.getParameter("groupId")));
        response.sendRedirect("StudentPageServlet");
    }

    public void learn(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    /**
     * Redirect the response to JoinGroupServlet
     * @param request Servlet request
     * @param response Servlet response
     * @throws IOException if an I/O error occurs
     */
    public void JoinGroup(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendRedirect("JoinGroupServlet");
    }

}
