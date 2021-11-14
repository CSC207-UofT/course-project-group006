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
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet testServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            Cookie[] c = request.getCookies();
        int userId=-1;
            for (Cookie cookie : c) {
                if (cookie.getName().equals("userId")) {
                    userId = Integer.parseInt(cookie.getValue());
                }
            }
        if(userId==-1){
            try{
            response.sendRedirect("LogInPage.html");
            
            }catch(IOException e){
                // Do nothing
            }
        }else{
            List<Integer> groups=userGroupManager.createdBy(userId);
            out.print(groups.size());
            for (Integer group : groups) {
                out.print("<form action=\"TeacherPageServlet\" method=\"Post\">");
                out.print("<label \">" + userGroupManager.getNameOfGroup(group));
                out.print("<input type=\"hidden\" name=\"groupId\" id=\"groupId\" value=" + group + ">");
                out.print("<input type=\"submit\" name=\"act\" id=\"act\" value=\"delete\">");
                out.print("<input type=\"submit\" name=\"act\" id=\"act\" value=\"detail\">");
                out.print("</form></br>");
            }
        }
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void delete(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("groupId"));
        userGroupManager.deleatGroup(id);
        try{
        response.sendRedirect("TeacherPageServlet");
        }catch(IOException e){
            // Do nothing
        }
    }
    public void detail(HttpServletRequest request, HttpServletResponse response){
        try{
        response.sendRedirect("GroupPageServlet?groupId="+request.getParameter("groupId"));
        }catch(IOException e){
            // Do nothing
        }
    }
    
}
