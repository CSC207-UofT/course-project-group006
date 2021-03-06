/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author darcy
 */
@WebServlet(name = "TestPageServlet", urlPatterns = {"/TestPageServlet"})
public class TestPageServlet extends TestServlet {

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
        List<Integer> ownedTest = teacherManager.tests(userId);
        for(Integer i:ownedTest){
            request.setAttribute("test"+i, testManager.getTestName(i));
        }
        request.setAttribute("ownedTest",ownedTest);
        request.setAttribute("userId", userId);
        request.setAttribute("userName", teacherManager.getNameById(userId));
        if(request.getParameter("groupId")!=null){
            request.setAttribute("groupId", Integer.parseInt(request.getParameter("groupId")));
        }
        RequestDispatcher r= request.getRequestDispatcher("TestPage.jsp");
        r.forward(request, response);
    }
    /**
     * Add test
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int testId = testManager.creatTest(request.getParameter("testName"), getUserId(request),0);
        teacherManager.addTest(getUserId(request),testId);
        processRequest(request,response);
    }
    /**
     * Redirect to TestDetailServlet
     *
     * @param request servlet request
     * @param response servlet response
     * @throws IOException if an I/O error occurs
     */
    public void detail(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendRedirect("TestDetailServlet?testId="+request.getParameter("testId"));
    }
    /**
     * Redirect to TeacherPageServlet
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void back(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("TeacherPageServlet");
    }
    /**
     * Redirect to choosingTime
     *
     * @param request servlet request
     * @param response servlet response
     * @throws IOException if an I/O error occurs
     */
    public void assign(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendRedirect("choosingTime.jsp?testId="+request.getParameter("testId")+"&groupId="+request.getParameter("groupId"));
    }
}
