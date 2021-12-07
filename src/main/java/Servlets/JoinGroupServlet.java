package Servlets;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */



import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author darcy
 */
@WebServlet(urlPatterns = {"/JoinGroupServlet"})
public class JoinGroupServlet extends TestServlet {

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
        //request.setAttribute("allGroups", groupManager.getAllGroup());
        HashMap<Integer,String> groups = groupManager.getAllGroup();
        HashMap<Integer,String> group1 = new HashMap<Integer,String>();
        List<Integer> joinedGroup = studentManager.getJoinedGroup(getUserId(request));
        for(Integer i:groups.keySet()){
            if(!joinedGroup.contains(i)){
                group1.put(i,groups.get(i));
            }
        }
        request.setAttribute("allGroups", group1);
        request.setAttribute("userId", getUserId(request));
        RequestDispatcher r = request.getRequestDispatcher("JoinGroup.jsp");
        r.forward(request, response);
    }

    /**
     *
     * @param request servlet request
     * @param response servlet response
     * @throws IOException if an I/O error occurs
     */
    public void join(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        groupManager.addStudentToGroup(getUserId(request), Integer.parseInt(request.getParameter("groupId")));
        studentManager.addGroupToUser(getUserId(request),Integer.parseInt(request.getParameter("groupId")), 500);
        response.sendRedirect("JoinGroupServlet");
    }
    public void back(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendRedirect("StudentPageServlet");
    }
}
