/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.catnix.servlets;

import com.catnix.beans.Prospect;
import com.catnix.dao.ProspectDao;
import com.catnix.dao.ProspectDaoImpl;
import java.io.IOException;
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
@WebServlet(name = "ShowProspectCard", urlPatterns = {"/ShowProspectCard"})
public class ShowProspectCard extends HttpServlet {
        public static final String VIEW_SHOW_PROSPECT_CARD = "/WEB-INF/showProspectCard.jsp";
    public static final String SESSION_PROSPECT_ATT = "prospect";  

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Prospect prospect = new Prospect();
        ProspectDao prospectDao = new ProspectDaoImpl();
        //long prospectid = Long.valueOf(request.getParameter("prospectid"));
        long prospectid =Long.parseLong(request.getParameter("prospectid"));        
        try {            
            prospect = prospectDao.find(prospectid);
        } catch (Exception ex) {
            Logger.getLogger(AddProspect.class.getName()).log(Level.SEVERE, null, ex);
        }
        session.setAttribute(SESSION_PROSPECT_ATT, prospect);     
        this.getServletContext().getRequestDispatcher(VIEW_SHOW_PROSPECT_CARD).forward(request, response);

    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
