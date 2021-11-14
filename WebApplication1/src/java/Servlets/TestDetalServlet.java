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
import QuestionTypes.QuestionInterface;
import java.util.List;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author darcy
 */
@WebServlet(name = "TestDetalServlet", urlPatterns = {"/TestDetalServlet"})
public class TestDetalServlet extends testServlet {

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
        List<QuestionInterface> questions = testManager.getTestInfo(Integer.parseInt(request.getParameter("testId")));
        request.setAttribute("testSize", questions.size());
        request.setAttribute("testId", Integer.parseInt(request.getParameter("testId")));
        for(int i=0; i<questions.size();i++){
            request.setAttribute("Q"+i+"question", questions.get(i).getQuestion());
            request.setAttribute("Q"+i+"answer", questions.get(i).getAnswer());
            request.setAttribute("Q"+i+"mark", questions.get(i).getMarks());
            
        }
        
        RequestDispatcher r= request.getRequestDispatcher("TestDetalPage.jsp");
        r.forward(request, response);
        
    }
    public void add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int testId=Integer.parseInt(request.getParameter("testId"));
        String question = request.getParameter("question");
        String answer = request.getParameter("answer");
        int mark = Integer.parseInt(request.getParameter("mark"));
        testManager.addQuestion(testId, question, answer, mark);
        processRequest(request,response);
    }
    public void delet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    int id = Integer.parseInt(request.getParameter("testId"));
    int questionNumber = Integer.parseInt(request.getParameter("questionNumber"));
    testManager.removeQuestion(id, questionNumber);
    processRequest(request,response);
    }
    public void back(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("TestPageServlet");
    }
}
