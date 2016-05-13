/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.catnix.servlets;

import com.catnix.beans.Member;
import com.catnix.beans.Tender;
import com.catnix.dao.DAOFactory;
import com.catnix.dao.TenderDao;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@WebServlet(name = "ConsultMyTenders", urlPatterns = "/consultMyTenders")
public class ConsultMyTenders extends HttpServlet {

    private static final String CONSULT_MY_TENDER_VIEW = "/WEB-INF/consultMyTenders.jsp";
    private static final String DAO_FACTORY_CONF = "daofactory";
    private static final String SESSION_TENDERS_ATT = "cpTenders";

    private TenderDao tenderDao;

    @Override
    public void init() throws ServletException {

        this.tenderDao = ((DAOFactory) getServletContext().getAttribute(DAO_FACTORY_CONF)).getTenderDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");
        String nameCp = member.getLastname() + " " + member.getFirstname();
        
        List<Tender> cpTenders = tenderDao.listCpTenders(nameCp);
        Map<Long, Tender> mapTenders = new HashMap<>();
        for (Tender tender : cpTenders) {
            mapTenders.put(tender.getId(), tender);
        }
        session.setAttribute(SESSION_TENDERS_ATT, cpTenders);

        this.getServletContext().getRequestDispatcher(CONSULT_MY_TENDER_VIEW).forward(request, response);
    }

}
