<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
=======
>>>>>>> cd425936a9d37ab614de8f70d747ddee34f68c77
package com.catnix.servlets;

import com.catnix.beans.Prospect;
import com.catnix.dao.ProspectDao;
import com.catnix.dao.ProspectDaoImpl;
import java.io.IOException;
import java.util.ArrayList;
<<<<<<< HEAD
import java.util.HashMap;
import java.util.Map;
=======
>>>>>>> cd425936a9d37ab614de8f70d747ddee34f68c77
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mélanie
 */
@WebServlet(name = "ShowProspect", urlPatterns = {"/ShowProspect"})
public class ShowProspect extends HttpServlet {
    public static final String VIEW_SHOW_PROSPECT_LIST = "/WEB-INF/showProspectsList.jsp";
    public static final String SESSION_PROSPECTS_ATT = "prospects";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<Prospect> allprospects = new ArrayList();
        ProspectDao prospectDao = new ProspectDaoImpl();
        
        try {            
            allprospects = prospectDao.list();
        } catch (Exception ex) {
            Logger.getLogger(AddProspect.class.getName()).log(Level.SEVERE, null, ex);
        }
<<<<<<< HEAD
        
        Map<Long, Prospect> mapProspects = new HashMap<>();
        for (Prospect prospect : allprospects) {
            mapProspects.put(prospect.getId(), prospect);
        }
       
        session.setAttribute(SESSION_PROSPECTS_ATT, allprospects);     
=======
       
        session.setAttribute(SESSION_PROSPECTS_ATT, allprospects);
        
>>>>>>> cd425936a9d37ab614de8f70d747ddee34f68c77
        this.getServletContext().getRequestDispatcher(VIEW_SHOW_PROSPECT_LIST).forward(request, response);

    }

<<<<<<< HEAD
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
=======
  
>>>>>>> cd425936a9d37ab614de8f70d747ddee34f68c77
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

<<<<<<< HEAD
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
=======

>>>>>>> cd425936a9d37ab614de8f70d747ddee34f68c77
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
