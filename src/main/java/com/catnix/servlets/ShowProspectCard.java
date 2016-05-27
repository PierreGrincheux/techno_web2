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
    public static final String VIEW_SHOW_PROSPECT_UPDATE = "/WEB-INF/validateUpdateProspect.jsp";
    public static final String SESSION_PROSPECT_ATT = "prospect";  
    public static final String SESSION_COMMENTS_ATT = "allcomments";
    public static final String ATT_FORM_PROSPECT = "formprospect";


    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        Prospect prospect = new Prospect();
        ProspectDao prospectDao = new ProspectDaoImpl();
        long prospectid =Long.parseLong(request.getParameter("prospectid"));        
        try {            
            prospect = prospectDao.find(prospectid);
        } catch (Exception ex) {
            Logger.getLogger(AddProspect.class.getName()).log(Level.SEVERE, null, ex);
        }
        session.setAttribute(SESSION_PROSPECT_ATT, prospect);
        
        ArrayList<Comment> allcomments = new ArrayList();
        CommentDao commentDao = new CommentDaoImpl();
        try {            
            allcomments = commentDao.list_for_prospect(prospectid);
        } catch (Exception ex) {
            Logger.getLogger(AddProspect.class.getName()).log(Level.SEVERE, null, ex);
        }
        session.setAttribute(SESSION_COMMENTS_ATT, allcomments);
        
        this.getServletContext().getRequestDispatcher(VIEW_SHOW_PROSPECT_CARD).forward(request, response);

    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Prospect prospect = new Prospect();
        ProspectForm prospectForm = new ProspectForm();
        
        try {
            prospect = prospectForm.updateProspect(request);

        } catch (Exception ex) {
            Logger.getLogger(AddProspect.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        request.setAttribute(ATT_FORM_PROSPECT, prospectForm);
        session.setAttribute(SESSION_PROSPECT_ATT, prospect);  
        response.sendRedirect("/catnix/ShowProspectCard?prospectid=" + prospect.getId());
        
    }

}
