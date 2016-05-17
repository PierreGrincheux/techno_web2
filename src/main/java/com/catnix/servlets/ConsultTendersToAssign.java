/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.catnix.servlets;

import com.catnix.beans.TenderToAssign;
import com.catnix.dao.DAOFactory;
import com.catnix.dao.TenderToAssignDao;
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
 * @author Nico
 */
@WebServlet(name = "ConsultTendersToAssign", urlPatterns = "/consultTendersToAssign")
public class ConsultTendersToAssign extends HttpServlet {

    private static final String ASSIGN_TENDER_TO_CP_VIEW = "/WEB-INF/consultTendersToAssign.jsp";
    private static final String DAO_FACTORY_CONF = "daofactory";
    private static final String SESSION_TENDERS_TO_ASSIGN_ATT = "tendersToAssign";

    private TenderToAssignDao tenderToAssignDao;

    @Override
    public void init() throws ServletException {

        this.tenderToAssignDao = ((DAOFactory) getServletContext().getAttribute(DAO_FACTORY_CONF)).getTenderToAssignDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        
        List<TenderToAssign> tendersToAssign = tenderToAssignDao.list();
      
        session.setAttribute(SESSION_TENDERS_TO_ASSIGN_ATT, tendersToAssign);

        this.getServletContext().getRequestDispatcher(ASSIGN_TENDER_TO_CP_VIEW).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

}
