/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pl.polsl.baconwebapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.polsl.baconwebapp.model.BaconModel;
import javax.servlet.http.*;

/**
 * handles the encryption and decryption part of the webpage
 * @author Mateusz Kies
 * @version 1.0
 */

@WebServlet("/Encrypt")
public class BaconEncryptServlet extends HttpServlet {

    
    
        /**  link to the model that handles encoding and decoding
    */   
    BaconModel model = new BaconModel();
    /**  vector that holds the history of encryptions in this session
    */ 
    Vector<String> encryptionsVector = new Vector<>();
    /**  vector that holds the history of decryptions in this session
    */ 
    Vector<String> decryptionsVector = new Vector<>();
    
    
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
            out.println("<body style='background-color:pink;'>");
            String message = request.getParameter("message");
            String action = request.getParameter("s1");
            HttpSession session = request.getSession();
            session.setAttribute("decrypt", decryptionsVector);
            session.setAttribute("encrypt", encryptionsVector); 
            if (message.length() == 0) {
            response.sendError(response.SC_BAD_REQUEST, "You need to input the message!");
            }
            else if (action.equals("Encrypt")){
           out.println("<html>\n<center>\n<h1><br><br><br><br><br><br><br><br><br><br>" + model.encode(message));
           encryptionsVector.add(message);   
            }
            else if (action.equals("Decrypt")){
           Boolean valid = model.validateData(message);
           if (valid){
           out.println("<html>\n<center>\n<h1><br><br><br><br><br><br><br><br><br><br>" + model.decode(message));
           decryptionsVector.add(message);
           }
           else{
           response.sendError(response.SC_BAD_REQUEST, "You need to input valid data!");
           }
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
}
