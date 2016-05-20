/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.catnix.servlets;

import com.catnix.beans.Tender;
import com.catnix.dao.DAOFactory;
import com.catnix.dao.TenderDao;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Fritsch
 */
@WebServlet(name = "HandleTender", urlPatterns = {"/handleTender"})
public class HandleTender extends HttpServlet {

    private static final String HANDLE_TENDER_VIEW = "/WEB-INF/handleTender.jsp";
    private static final String DAO_FACTORY_CONF = "daofactory";
    private static final String SESSION_TENDERS_ATT = "tenders";

    private TenderDao tenderDao;

    @Override
    public void init() throws ServletException {

        this.tenderDao = ((DAOFactory) getServletContext().getAttribute(DAO_FACTORY_CONF)).getTenderDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        
        List<Tender> tenders = tenderDao.listTenderToHandle();
        
        session.setAttribute(SESSION_TENDERS_ATT, tenders);
        
        this.getServletContext().getRequestDispatcher(HANDLE_TENDER_VIEW).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}
