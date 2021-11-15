/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import QuestionTypes.QuestionInterface;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
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
@WebServlet(name = "TestSubmitionServlet", urlPatterns = {"/TestSubmitionServlet"})
public class TestSubmitionServlet extends testServlet {

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
        int testId = Integer.parseInt(request.getParameter("testId"));
        request.setAttribute("testId", testId);
        int groupId = Integer.parseInt(request.getParameter("groupId"));
        request.setAttribute("groupId", groupId);
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        request.setAttribute("studentId", studentId);;
        if(userGroupManager.getSubmition(groupId, testId)!=null){
        String[] answers = userGroupManager.getSubmition(groupId, testId).get(studentId);
        request.setAttribute("answers", answers);
        }
        List<QuestionInterface> questions = testManager.getTestInfo(testId);
        String[] question = testManager.getQuestions(testId);
        String[] answer = testManager.getAnswers(testId);
        int[] mark = testManager.getMarks(testId);
        request.setAttribute("questions", questions);
        request.setAttribute("question", question);
        request.setAttribute("answer", answer);
        request.setAttribute("mark", mark);
        RequestDispatcher r= request.getRequestDispatcher("TestSubmiation.jsp");
        r.forward(request, response);
        
    }
    public void grade(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int testSize= Integer.parseInt(request.getParameter("testSize"));
        int testId= Integer.parseInt(request.getParameter("testId"));
        int groupId= Integer.parseInt(request.getParameter("groupId"));
        int studentId= Integer.parseInt(request.getParameter("studentId"));
        int[] grades= new int[testSize];
        for(int i=0;i<testSize;i++){
            grades[i]=Integer.parseInt(request.getParameter("Q"+i+"grade"));
        }
    }
   public void back(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("GroupPageServlet?groupId="+request.getParameter("groupId"));
   }
}
