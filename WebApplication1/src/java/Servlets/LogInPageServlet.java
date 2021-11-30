//package Servlets;
//
///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
//
//import static Servlets.testServlet.userGroupManager;
//import java.io.IOException;
//import java.io.PrintWriter;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.Cookie;
///**
// *
// * @author darcy
// */
//@WebServlet(urlPatterns = {"/LogInPageServlet"})
//public class LogInPageServlet extends testServlet {
//
//    public void logIn(HttpServletRequest request, HttpServletResponse response){
//        int result = userManager.loginWithUsername(request.getParameter("username"), request.getParameter("password"));
//        if(result==-1){
//            try {
//            PrintWriter out = response.getWriter();
//            //out.print(userManager.loginWithUsername(request.getParameter("username"), request.getParameter("password")));
//            out.println("<script type=\"text/javascript\">");
//            out.print("window.location='LogInPage.html';");
//            out.println("alert('wrong username or password');");
//            out.println("</script>");
//        }catch(IOException e){
//            // Do nothing
//        }
//        }else{
//
//            String userType = userManager.getUserType(result);
//            Cookie userId=new Cookie("userId",""+result);
//            response.addCookie(userId);
//            if(userType.equals("T")){
//                try{
//                response.sendRedirect("TeacherPageServlet");
//                }catch(IOException e){
//                    // Do nothing
//                }
//            }else if(userType.equals("S")){
//                try{
//                response.sendRedirect("StudentPageServlet");
//                }catch(IOException e){
//                    // Do nothing
//                }
//            }
//        }
//    }
//    public void register(HttpServletRequest request, HttpServletResponse response){
//        try{
//            response.sendRedirect("register.jsp");
//        }catch(IOException e){
//            // Do nothing
//        }
//
//    }
//
//
//
//}
