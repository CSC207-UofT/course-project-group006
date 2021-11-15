/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import QuestionTypes.QuestionInterface;
import static Servlets.testServlet.testManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet(name = "TestPresenterServlet", urlPatterns = {"/TestPresenterServlet"})
public class TestPresenterServlet extends testServlet {

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
        //List<QuestionInterface> questions = testManager.getTestInfo(Integer.parseInt(request.getParameter("testId")));
        String[] questions = testManager.getQuestions(Integer.parseInt(request.getParameter("testId")));
        int[] marks = testManager.getMarks(Integer.parseInt(request.getParameter("testId")));
        request.setAttribute("testSize", questions.length);
        request.setAttribute("testId", Integer.parseInt(request.getParameter("testId")));
        for(int i=0; i<questions.length;i++){
            request.setAttribute("Q"+i+"question", questions[i]);
            request.setAttribute("Q"+i+"mark", questions[i]);
        }
        
        RequestDispatcher r= request.getRequestDispatcher("TestPresenter.jsp");
        r.forward(request, response);
    }
    public void submit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    int groupId = Integer.parseInt(request.getParameter("groupId"));
    int testId = Integer.parseInt(request.getParameter("testId"));
    int testSize = Integer.parseInt(request.getParameter("testSize"));
    String[] answer = new String[testSize];
    for(int i=0;i<testSize;i++){
        answer[i]=request.getParameter("Q"+i+"answer");
    }
    userGroupManager.answerTest(groupId, testId, answer, getUserId(request));
    response.sendRedirect("GroupPageServlet?groupId="+groupId);
    }
    
}
