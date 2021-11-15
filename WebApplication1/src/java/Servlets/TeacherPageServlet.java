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
import javax.servlet.http.Cookie;
import java.util.List;

/**
 *
 * @author darcy
 */
@WebServlet(name = "TeacherPageServlet", urlPatterns = {"/TeacherPageServlet"})
public class TeacherPageServlet extends testServlet {
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet testServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            Cookie c[] = request.getCookies();
        int userId=-1;
        for(int i=0;i<c.length;i++){
            if(c[i].getName().equals("userId")){
                userId=Integer.parseInt(c[i].getValue());
            }
        }
        if(userId==-1){
            try{
            response.sendRedirect("LogInPage.html");
            
            }catch(IOException e){
                
            }
        }else{
            List<Integer> groups=userGroupManager.createdBy(userId);
            out.print(userManager.getName(userId)+"'s page:<br>groups:<br>");
            for(int i=0; i<groups.size();i++){
             out.print("<form action=\"TeacherPageServlet\" method=\"Post\">");
            out.print("<label \">"+userGroupManager.getNameOfGroup(groups.get(i)));
            out.print("<input type=\"hidden\" name=\"groupId\" id=\"groupId\" value="+groups.get(i)+">");
            out.print("<input type=\"submit\" name=\"act\" id=\"act\" value=\"deleat\">");
            out.print("<input type=\"submit\" name=\"act\" id=\"act\" value=\"detal\">");
            out.print("</form></br>");
            }
            out.print("<form action=\"TeacherPageServlet\" method=\"Post\" name=\"creatGroup\" id = \"creatGroup\">");
            out.print("<input type=\"text\" name=\"testName\" id=\"testName\">");
            out.print("<input type=\"submit\" name=\"act\" id=\"act\" value=\"Creat\">");
            out.print("</form></br>");
            out.print("<form action=\"TeacherPageServlet\" method=\"Post\">");
            out.print("<input type=\"submit\" name=\"act\" id=\"act\" value=\"Tests\">");
            out.print("</form></br>");
            
        }
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void deleat(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("groupId"));
        userGroupManager.deleatGroup(id);
        try{
        response.sendRedirect("TeacherPageServlet");
        }catch(IOException e){
                    
        }
    }
    public void detal(HttpServletRequest request, HttpServletResponse response){
        try{
        response.sendRedirect("GroupPageServlet?groupId="+request.getParameter("groupId"));
        }catch(IOException e){
                   
        }
    }
    public void Tests(HttpServletRequest request, HttpServletResponse response){
        try{
        response.sendRedirect("TestPageServlet");
        }catch(IOException e){
                   
        }
    }
    public void Creat(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        userGroupManager.creatGroup(getUserId(request), request.getParameter("testName"));
        response.sendRedirect("TeacherPageServlet");
    }
    
}
