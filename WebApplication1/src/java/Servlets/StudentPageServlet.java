/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import static Servlets.testServlet.userGroupManager;
import static Servlets.testServlet.userManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
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
@WebServlet(name = "StudentPageServlet", urlPatterns = {"/StudentPageServlet"})
public class StudentPageServlet extends testServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = getUserId(request);
        if(userId==-1){
            try{
            response.sendRedirect("LogInPage.html");
            
            }catch(IOException e){
                
            }
        }
        request.setAttribute("joinedGroup", userGroupManager.getJoinedGroup(userId));
        request.setAttribute("userId", userId);
        RequestDispatcher r = request.getRequestDispatcher("StudentPage.jsp");
        r.forward(request, response);
    }
    public void search(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    public void detal(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("GroupPageServlet?groupId="+request.getParameter("groupId"));
    }
    public void quit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        userGroupManager.removeStudentFromGroup(getUserId(request), Integer.parseInt(request.getParameter("groupId")));
        response.sendRedirect("StudentPageServlet");
    }
    public void learn(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    public void JoinGroup(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("JoinGroupServlet");
    }
    
}
