package Servlets;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import BackEnd.Managers.GroupManager;
import BackEnd.Managers.*;
import BackEnd.Gateways.*;
import javax.servlet.http.Cookie;
/**
 *
 * @author darcy
 */
@WebServlet(urlPatterns = {"/TestServlet"})
public class TestServlet extends HttpServlet {
    protected static GroupManager groupManager=new GroupManager(new GroupGateway());
    protected static TeacherManager teacherManager=new TeacherManager(new TeacherGateway());
    protected static StudentManager studentManager=new StudentManager(new StudentGateway());
    protected static QuestionManager questionManager= new QuestionManager(new QuestionGateway());
    protected static TestManager testManager= new TestManager(new TestGateway(),questionManager);

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
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TestServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TestServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Method never used
     * @param request Servlet request
     * @param response Servlet response
     */
    protected void doGetAa(HttpServletRequest request, HttpServletResponse response){

    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        //processRequest(request, response);

        try{
            Method m= this.getClass().getDeclaredMethod(request.getParameter("act"), HttpServletRequest.class,HttpServletResponse.class);
            //p(m.getName(),response);
            m.invoke(this,request, response);
        }catch(NoSuchMethodException|IllegalAccessException|InvocationTargetException e){
            //p("no such method"+request.getParameter("act"),response);
            p(Arrays.toString(e.getStackTrace()),response);
            //p(request.getParameter("act"),response);
        }
    }
    protected void p(String input,HttpServletResponse response){
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>error</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>" + input + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }catch(Exception e){

            //System.out.print();
        }
    }
    protected void a(HttpServletRequest request, HttpServletResponse response){
        try {
            response.sendRedirect("TestWabPage.html");
        }catch(IOException  e){
            // Do nothing
        }
    }
    protected void b(HttpServletRequest request, HttpServletResponse response){

        try {
            response.sendRedirect("index.html");
        }catch(IOException  e){
            // Do nothing
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    /**
     * Get users ID
     *
     * @param request servlet request
     */
    public int getUserId(HttpServletRequest request){
        Cookie[] c = request.getCookies();
        for (Cookie cookie : c) {
            if (cookie.getName().equals("userId")) {
                return Integer.parseInt(cookie.getValue());
            }
        }
        return -1;
    }
    /**
     * Get type of users
     *
     * @param request servlet request
     */
    public String getUserType(HttpServletRequest request){
        Cookie[] c = request.getCookies();
        for (Cookie cookie : c) {
            if (cookie.getName().equals("userType")) {
                return cookie.getValue();
            }
        }
        return "";
    }
}
