/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.controller;

import evoting.dao.CandidateDao;
import evoting.dto.AddCandidateDto;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

/**
 *
 * @author Win7
 */
public class AddNewCandidatesControllerServlet extends HttpServlet {

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
        try {
            System.out.println("AddNewCandidateControllerServlet try \n");
            DiskFileItemFactory dif = new DiskFileItemFactory();
            ServletRequestContext srq = new ServletRequestContext(request);
            ServletFileUpload sfu = new ServletFileUpload(dif);
            List <FileItem> multiparts = sfu.parseRequest(srq);
            ArrayList <String> objValues = new ArrayList<>();
            InputStream fileContent = null;
            int i =0;
            for(FileItem item : multiparts) {
                if(item.isFormField()) {
                    i++;
                    System.out.println("Add new Candid Servl ka if chla\n\n");
                    String fieldName = item.getFieldName();
                    String fieldValue = item.getString();
                    System.out.println(fieldValue+","+i);
                    objValues.add(fieldValue);
                }
                else {
                    i++;
                    System.out.println("Add new candid servlet ka else chla\n\n");
                    String fieldName = item.getFieldName();
                    String fileName = item.getName();
                    fileContent = item.getInputStream(); 
                    System.out.println(fieldName+","+i);
                }   
            }
            
            if(fileContent == null) {
                AddCandidateDto candidate = new AddCandidateDto(objValues.get(0), objValues.get(3), objValues.get(4), objValues.get(1), fileContent);
                boolean result = CandidateDao.addCandidate(candidate);
            }
            else {
                AddCandidateDto candidate = new AddCandidateDto(objValues.get(0), objValues.get(3), objValues.get(4), objValues.get(1), fileContent);
                boolean result = CandidateDao.addCandidate(candidate);
            }
            System.out.println(objValues.get(0)+","+ objValues.get(3)+","+ objValues.get(4)+","+ objValues.get(1)+","+ fileContent);
            AddCandidateDto candidate = new AddCandidateDto(objValues.get(0), objValues.get(3), objValues.get(4), objValues.get(1), fileContent);
            boolean result = CandidateDao.addCandidate(candidate);
            System.out.println(result+" result from candidateDao.add candidate in addcontrolservlet\n\n");
            if(result == true) 
                rd = request.getRequestDispatcher("success.jsp");            
            else 
                rd = request.getRequestDispatcher("failure.jsp");                        
        }
        catch(Exception ex) {
            request.getRequestDispatcher("showexception.jsp");
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
