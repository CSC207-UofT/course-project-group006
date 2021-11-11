/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author darcy
 */
@WebServlet(name = "regesterServlet", urlPatterns = {"/regesterServlet"})
public class regesterServlet extends testServlet {

    public void teacher(HttpServletRequest request, HttpServletResponse response){
        userManager.createTeacher(request.getParameter("username"), request.getParameter("password"), request.getParameter("email"));
        try{
            response.sendRedirect("LogInPage.html");
        }catch(IOException e){
            
        }
    }
    public void student(HttpServletRequest request, HttpServletResponse response){
        userManager.createStudent(request.getParameter("username"), request.getParameter("password"), request.getParameter("email"));
        try{
            response.sendRedirect("LogInPage.html");
        }catch(IOException e){
            
        }
    }

}
