/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pl.polsl.baconwebapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * handles the history viewing part of the webpage
 * @author Mateusz Kies
 * @version 1.0
 */

@WebServlet("/History")
public class BaconHistoryServlet extends HttpServlet {

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
         int timesVisited = 1;   
         HttpSession session = request.getSession();
         Vector<String> lastEncrypt = (Vector)session.getAttribute("encrypt");
         Vector<String> lastDecrypt = (Vector)session.getAttribute("decrypt");
         
         out.println("<html>\n<body>\n<h1>"+"Your last encryptions were:<br>");
         for (int i = 0; i < lastEncrypt.size(); i++)
         {
            out.println(lastEncrypt.get(i) + "<br>");
         }
         out.println("<html>\n<body>\n<h1>\n<br>"+"Your last decryption were:<br>");
         for (int i = 0; i < lastDecrypt.size(); i++)
         {
            out.println(lastDecrypt.get(i) + "<br>");
         }
         
         Cookie[] cookies = request.getCookies();
         if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("historyVisited")) {
                        timesVisited = Integer.parseInt(cookie.getValue()) + 1;
                        break;
                    }
                }
            }
         
         Cookie cookie = new Cookie("historyVisited", Integer.toString(timesVisited));
         response.addCookie(cookie);
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
