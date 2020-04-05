/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.controller;

import evoting.dao.VoteDao;
import evoting.dto.VoteDto;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Win7
 */
public class AddVoteControllerServlet extends HttpServlet {

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
        System.out.println("Add vote controller servlet\n");
        RequestDispatcher rd = null;
        HttpSession session = request.getSession();
        String userid = (String)session.getAttribute("userid");
        String candidateId = (String)request.getParameter("candidateid");
        System.out.println("\nUserid from add vote controller servlrft " + userid +" ye aaya\n");
        if(userid == null) {
            session.invalidate();
            response.sendRedirect("accessdenied");
        }
        else {
            try {
                boolean result;
                if(VoteDao.validateVote(userid))
                    result = false;
                else {
                    VoteDto vote = new VoteDto(candidateId, userid);
                    result = VoteDao.addVote(vote);
                    System.out.println("add vote con ser ke else ka result "+ result + " ye h");
                }
                request.setAttribute("result", result);
                System.out.println("Verifyvote jsp pe gya");
                rd = request.getRequestDispatcher("verifyvote.jsp");
            }
            catch(Exception ex) {
                System.out.println("Add vote cont ser catch");
                ex.printStackTrace();
                request.setAttribute("exception", ex);
                rd = request.getRequestDispatcher("showexception.jsp");
            }
            finally {
                rd.forward(request, response);
            }
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
