package com.catnix.servlets;

import com.catnix.beans.Prospect;
import com.catnix.dao.ProspectDao;
import com.catnix.dao.ProspectDaoImpl;
import java.io.IOException;
import java.util.ArrayList;
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
       
        session.setAttribute(SESSION_PROSPECTS_ATT, allprospects);
        
        this.getServletContext().getRequestDispatcher(VIEW_SHOW_PROSPECT_LIST).forward(request, response);

    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
