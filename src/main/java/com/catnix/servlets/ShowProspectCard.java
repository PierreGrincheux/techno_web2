<<<<<<< HEAD
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
=======
package com.catnix.servlets;

import com.catnix.beans.Comment;
import com.catnix.beans.Prospect;
import com.catnix.dao.CommentDao;
import com.catnix.dao.CommentDaoImpl;
import com.catnix.dao.ProspectDao;
import com.catnix.dao.ProspectDaoImpl;
import com.catnix.forms.ProspectForm;
import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "ShowProspectCard", urlPatterns = {"/ShowProspectCard"})
public class ShowProspectCard extends HttpServlet {
<<<<<<< HEAD
        public static final String VIEW_SHOW_PROSPECT_CARD = "/WEB-INF/showProspectCard.jsp";
    public static final String SESSION_PROSPECT_ATT = "prospect";  
=======
    public static final String VIEW_SHOW_PROSPECT_CARD = "/WEB-INF/showProspectCard.jsp";
    public static final String SESSION_PROSPECT_ATT = "prospect";  
    public static final String SESSION_COMMENTS_ATT = "allcomments";
    public static final String VIEW_UPDATED = "/WEB-INF/validateUpdateProspect.jsp";
>>>>>>> cd425936a9d37ab614de8f70d747ddee34f68c77

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
<<<<<<< HEAD
        Prospect prospect = new Prospect();
        ProspectDao prospectDao = new ProspectDaoImpl();
        //long prospectid = Long.valueOf(request.getParameter("prospectid"));
=======
        
        Prospect prospect = new Prospect();
        ProspectDao prospectDao = new ProspectDaoImpl();
>>>>>>> cd425936a9d37ab614de8f70d747ddee34f68c77
        long prospectid =Long.parseLong(request.getParameter("prospectid"));        
        try {            
            prospect = prospectDao.find(prospectid);
        } catch (Exception ex) {
            Logger.getLogger(AddProspect.class.getName()).log(Level.SEVERE, null, ex);
        }
<<<<<<< HEAD
        session.setAttribute(SESSION_PROSPECT_ATT, prospect);     
=======
        session.setAttribute(SESSION_PROSPECT_ATT, prospect);
        
        ArrayList<Comment> allcomments = new ArrayList();
        CommentDao commentDao = new CommentDaoImpl();
        try {            
            allcomments = commentDao.list_for_prospect(prospectid);
        } catch (Exception ex) {
            Logger.getLogger(AddProspect.class.getName()).log(Level.SEVERE, null, ex);
        }
        session.setAttribute(SESSION_COMMENTS_ATT, allcomments);
        
>>>>>>> cd425936a9d37ab614de8f70d747ddee34f68c77
        this.getServletContext().getRequestDispatcher(VIEW_SHOW_PROSPECT_CARD).forward(request, response);

    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
<<<<<<< HEAD

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
=======
        HttpSession session = request.getSession();
        Prospect prospect = new Prospect();
        ProspectForm prospectForm = new ProspectForm();
        
        try {
            prospect = prospectForm.updateProspect(request);

        } catch (Exception ex) {
            Logger.getLogger(AddProspect.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    
        session.setAttribute(SESSION_PROSPECT_ATT, prospect);  
        this.getServletContext().getRequestDispatcher(VIEW_UPDATED).forward(request, response);
        
    }

>>>>>>> cd425936a9d37ab614de8f70d747ddee34f68c77
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
