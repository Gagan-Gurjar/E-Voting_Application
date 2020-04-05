/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.controller;

import evoting.dao.CandidateDao;
import evoting.dao.UserDao;
import evoting.dto.UserDetails;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HP
 */
public class RemoveUserControllerServlet extends HttpServlet {

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
        RequestDispatcher rd = null;
        String data = (String)request.getParameter("data");
        System.out.println("\n\ndata = " +data);
        HttpSession session = request.getSession();
        String userid = (String)session.getAttribute("userid");
        System.out.println(data.length()+"  length aa rhi h");
        
        if(userid == null) {
            session.invalidate();
            response.sendRedirect("accessdenied.html");
            return;
        }
        try {
            System.out.println("show candid contro sewrvlre ka try\n");
            if(data != null && data.equals("uid")) {
                ArrayList <String> userId = UserDao.getUserId();
                request.setAttribute("userId", userId);
                request.setAttribute("result", "userList");
            }
            else if(data.charAt(0) == 'r' && data.charAt(data.length()-1) == 'r') {
                String id = data.substring(1, data.length()-1);
                boolean candidateResult = CandidateDao.deleteCandidateByUserId(id);
                boolean userResult = UserDao.removeUser(id);
                if(userResult == true) {
                    request.setAttribute("result", "success");
                    rd = request.getRequestDispatcher("AdminRemoveUser.jsp"); 
                }
                     
                else 
                    rd = request.getRequestDispatcher("failure.jsp");
                
            }
            else {
                UserDetails user = UserDao.getUserDetailsById(data);
                request.setAttribute("result", "details");
                request.setAttribute("user", user);
            }
            rd = request.getRequestDispatcher("AdminRemoveUser.jsp");
        }
        catch(Exception ex) {
            request.setAttribute("exception", ex);
            rd = request.getRequestDispatcher("showexception.jsp");
            ex.printStackTrace();
        }
        finally {
            rd.forward(request, response);
        }
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
        processRequest(request, response);
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

}
