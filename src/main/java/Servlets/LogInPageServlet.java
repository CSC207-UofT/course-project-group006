package Servlets;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import BackEnd.Gateways.StudentGateway;
import BackEnd.Gateways.TeacherGateway;
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

    public void logIn(HttpServletRequest request, HttpServletResponse response){
        int result =studentManager.loginWithUsername(request.getParameter("username"), request.getParameter("password"),
                new StudentGateway(),new TeacherGateway());
        if(result==-1){
            try {
                PrintWriter out = response.getWriter();
                //out.print(userManager.loginWithUsername(request.getParameter("username"), request.getParameter("password")));
                out.println("<script type=\"text/javascript\">");
                out.print("window.location='LogInPage.html';");
                out.println("alert('wrong username or password');");
                out.println("</script>");

                //out.println(new UserManager(new StudentGateway()).loginWithUsername
                        //("a","a",new StudentGateway(),new TeacherGateway()));
            }catch(IOException e){
                // Do nothing
            }
        }else{

            String userType = studentManager.getUserType(result);
            Cookie userId=new Cookie("userId",""+result);
            response.addCookie(userId);
            if(userType.equals("T")){
                try{
                    response.sendRedirect("TeacherPageServlet");
                }catch(IOException e){
                    // Do nothing
                }
            }else if(userType.equals("S")){
                try{
                    response.sendRedirect("StudentPageServlet");
                }catch(IOException e){
                    // Do nothing
                }
            }
        }
    }
    public void register(HttpServletRequest request, HttpServletResponse response){
        try{
            response.sendRedirect("register.jsp");
        }catch(IOException e){
            // Do nothing
        }

    }
}
