package Servlets;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author darcy
 */
@WebServlet(urlPatterns = {"/JoinGroupServlet"})
public class JoinGroupServlet extends testServlet {

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
        request.setAttribute("allGroups", userGroupManager.getAllGroup());
        request.setAttribute("userId", getUserId(request));
        RequestDispatcher r = request.getRequestDispatcher("JoinGroup.jsp");
        r.forward(request, response);
    }
    public void join(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        userGroupManager.addStudentToGroup(getUserId(request), Integer.parseInt(request.getParameter("groupId")));
        response.sendRedirect("JoinGroupServlet");
    }


}
