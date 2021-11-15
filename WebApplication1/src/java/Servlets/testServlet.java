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
import java.lang.reflect.Type;
import java.util.Locale;
import static java.lang.System.out;
import static java.lang.System.err;
import BackEnd1.UserGroupManager;
import BackEnd1.UserManager;
import BackEnd1.TestManager;
import BackEnd1.WordManager;
import javax.servlet.http.Cookie;
/**
 *
 * @author darcy
 */
@WebServlet(urlPatterns = {"/testServlet"})
public class testServlet extends HttpServlet {
    protected static UserGroupManager userGroupManager=new UserGroupManager();
    protected static UserManager userManager=new UserManager(null);
    protected static TestManager testManager= new TestManager();
    protected static WordManager wordManager=new WordManager(System.getProperty("user.dir") + "\\src\\java\\main\\Files\\Words");;
    
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
            out.println("<title>Servlet testServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet testServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
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
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        
            try{
            Method m= this.getClass().getDeclaredMethod(request.getParameter("act"), HttpServletRequest.class,HttpServletResponse.class);
            m.invoke(this,request, response);
            }catch(NoSuchMethodException|IllegalAccessException|InvocationTargetException e){
                p("no such method"+request.getParameter("act"),response);
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
        response.sendRedirect("testWabPage.html");
    }catch(IOException  e){
       System.out.print(e);
        //ServletException, IOException 
    }
    }
    protected void b(HttpServletRequest request, HttpServletResponse response){
        
        try {
            response.sendRedirect("index.html");
        }catch(IOException  e){
            System.out.print(e);
        //ServletException, IOException 
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
    public int getUserId(HttpServletRequest request){
        Cookie c[] = request.getCookies();
        int userId=-1;
        for(int i=0;i<c.length;i++){
            if(c[i].getName().equals("userId")){
                return Integer.parseInt(c[i].getValue());
            }
        }
        return -1;
    }
}
