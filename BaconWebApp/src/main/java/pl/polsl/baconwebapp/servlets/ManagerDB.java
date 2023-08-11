/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pl.polsl.baconwebapp.servlets;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServlet;
import java.sql.DatabaseMetaData;
import java.util.List;
import pl.polsl.baconwebapp.entities.HistoryInfo;

/**
 * @author Mateusz Kies
 * @version 1.0
 */
public class ManagerDB extends HttpServlet {

    void add(EntityManager em, HistoryInfo hi)
    {
        try {
            em.getTransaction().begin();
            em.persist(hi);
            em.getTransaction().commit();
        }
        catch(PersistenceException e) {
            em.getTransaction().rollback();
        }
    }

    void read(EntityManager em)
    {
        try {
            List<HistoryInfo> hist = em.createQuery("SELECT h FROM HistoryInfo h").getResultList();
            for(HistoryInfo h : hist) {
                System.out.println(h.getText() + h.getId() + "<br/>");
            }
        }
        catch(PersistenceException e) {
            em.getTransaction().rollback();
        }
    }

}