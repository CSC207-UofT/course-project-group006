package Servlets;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import BackEnd.Gateways.StudentGateway;
import BackEnd.Gateways.TeacherGateway;
import BackEnd.Managers.TeacherManager;
import BackEnd.Managers.UserManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
/**
 *
 * @author darcy
 */
@WebServlet(urlPatterns = {"/LogInPageServlet"})

public class LogInPageServlet extends TestServlet {
    /**
     * Method to be called when user logins
     * @param request Servlet request
     * @param response Servlet response
     */
    public void Login(HttpServletRequest request, HttpServletResponse response){
        int result =studentManager.LogIn(request.getParameter("username"), request.getParameter("password"));
        System.out.println(result);
        if(!(result==-1)){
            Cookie userId=new Cookie("userId",""+result);
            response.addCookie(userId);
            try{
                response.sendRedirect("StudentPageServlet");
            }catch(IOException e){
                // Do nothing
            }
        }
        result = teacherManager.LogIn(request.getParameter("username"), request.getParameter("password"));
        if(!(result==-1)){
            Cookie userId=new Cookie("userId",""+result);
            response.addCookie(userId);
            try{
                response.sendRedirect("TeacherPageServlet");
            }catch(IOException e){
                // Do nothing
            }
            //String userType = studentManager.getUserType(result);


        }
        try {
            PrintWriter out = response.getWriter();
            //out.print(userManager.loginWithUsername(request.getParameter("username"), request.getParameter("password")));
            //out.println("<script type=\"text/javascript\">");
            //out.print("window.location='LogInPage.html';");
            //out.println("alert('wrong username or password');");
            //out.println("</script>");

            out.println(result);
        }catch(IOException e){
            // Do nothing
        }

    }

    /**
     * Method to be called when users need to register
     * @param request Servlet request
     * @param response Servlet response
     */
    public void Register(HttpServletRequest request, HttpServletResponse response){
        try{
            response.sendRedirect("register.jsp");
        }catch(IOException e){
            // Do nothing
        }

    }
}
