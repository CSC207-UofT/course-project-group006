/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author darcy
 */
@WebServlet(name = "TestPageServlet", urlPatterns = {"/TestPageServlet"})
public class TestPageServlet extends testServlet {

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
        int userId=getUserId(request);
        ArrayList<Integer> ownedTest = testManager.getOwnedTest(userId);
        for(Integer i:ownedTest){
            request.setAttribute("test"+i, testManager.getTestName(i));
        }
        request.setAttribute("ownedTest",ownedTest);
        request.setAttribute("userId", userId);
        request.setAttribute("userName", userManager.getName(userId));
        if(request.getParameter("groupId")!=null){
            request.setAttribute("groupId", Integer.parseInt(request.getParameter("groupId")));
        }
        RequestDispatcher r= request.getRequestDispatcher("TestPage.jsp");
        r.forward(request, response);
    }
    
    public void add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    testManager.creatExame(request.getParameter("testName"),Integer.parseInt(request.getParameter("timeLimit")), getUserId(request),0);
    processRequest(request,response);
    }
    public void detal(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("TestDetalServlet?testId="+request.getParameter("testId"));
    }
    public void back(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("TeacherPageServlet");
    }
    public void asign(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("choosingTime.jsp?testId="+request.getParameter("testId")+"&groupId="+request.getParameter("groupId"));
    }
}
