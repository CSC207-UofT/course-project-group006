/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author darcy
 */
@WebServlet(name = "TestDetailServlet", urlPatterns = {"/TestDetailServlet"})
public class TestDetailServlet extends TestServlet {

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
        String[] answers = testManager.getAnswers(Integer.parseInt(request.getParameter("testId")));
        int[] marks = testManager.getMarks(Integer.parseInt(request.getParameter("testId")));
        int[] questionIds = testManager.getQuestionId(Integer.parseInt(request.getParameter("testId")));
        request.setAttribute("testSize", questionIds.length);
        request.setAttribute("testId", Integer.parseInt(request.getParameter("testId")));
        request.setAttribute("questionIds", questionIds);
        for (int questionId : questionIds) {
            request.setAttribute("Q" + questionId + "question", questionManager.getquestion(questionId));
            request.setAttribute("Q" + questionId + "answer", questionManager.getAnswer(questionId));
            request.setAttribute("Q" + questionId + "mark", questionManager.getMark(questionId));

        }

        RequestDispatcher r= request.getRequestDispatcher("TestDetalPage.jsp");
        r.forward(request, response);

    }

    /**
     * Method that allows users to add questions to test
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int testId=Integer.parseInt(request.getParameter("testId"));
        String question = request.getParameter("question");
        String answer = request.getParameter("answer");
        int mark = Integer.parseInt(request.getParameter("mark"));
        testManager.addQuestion(testId, question, answer, mark);
        processRequest(request,response);
    }

    /**
     * Method allowing users to remove questions from test
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void delet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("testId"));
        int questionId = Integer.parseInt(request.getParameter("questionId"));
        testManager.removeQuestion(id, questionId);
        processRequest(request,response);
    }

    /**
     * Redirect the response to TestPageServlet
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void back(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("TestPageServlet");
    }
}
