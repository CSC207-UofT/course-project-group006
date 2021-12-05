/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author darcy
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends TestServlet {

    public void teacher(HttpServletRequest request, HttpServletResponse response){
        teacherManager.createUser(request.getParameter("username"), request.getParameter("password"), request.getParameter("email"));
        try{
            response.sendRedirect("LogInPage.html");
        }catch(IOException e){
            // Do nothing
        }
    }
    public void student(HttpServletRequest request, HttpServletResponse response){
        studentManager.createUser(request.getParameter("username"), request.getParameter("password"), request.getParameter("email"));
        try{
            response.sendRedirect("LogInPage.html");
        }catch(IOException e){
            // Do nothing
        }
    }

}
