/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.catnix.servlets;

import com.catnix.beans.Tender;
import com.catnix.beans.TenderToAssign;
import com.catnix.dao.DAOFactory;
import com.catnix.dao.TenderToAssignDao;
import com.catnix.forms.PositionOnTenderForm;
import com.catnix.methods.Methods;
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
 * @author Fritsch
 */
@WebServlet(name = "PositionOnTender", urlPatterns = "/positionOnTender")
public class PositionOnTender extends HttpServlet {

    private static final String DAO_FACTORY_CONF = "daofactory";
    private static final String TARGETED_TENDER_ATT = "targetedTender";
    private static final String FORM_POSTION_ON_TENDER_ATT = "positionOnTenderForm";
    private static final String TENDER_TO_ASSIGN_ATT = "tenderToAssign";
    private static final String ID_TENDER_PARAM = "idTender";
    private static final String TITLE_TENDER_PARAM = "title";
    private static final String POSITION_ON_TENDER_VIEW = "/WEB-INF/positionOnTender.jsp";

    private TenderToAssignDao tenderToAssignDao;

    @Override
    public void init() throws ServletException {

        this.tenderToAssignDao = ((DAOFactory) getServletContext().getAttribute(DAO_FACTORY_CONF)).getTenderToAssignDao();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String idTender = Methods.getFieldValue(request, ID_TENDER_PARAM);
        String title = Methods.getFieldValue(request, TITLE_TENDER_PARAM);
        Long id = Long.parseLong(idTender);

        Tender targetedTender = new Tender();
        targetedTender.setId(id);
        targetedTender.setTitle(title);

        session.setAttribute(TARGETED_TENDER_ATT, targetedTender);

        this.getServletContext().getRequestDispatcher(POSITION_ON_TENDER_VIEW).forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        PositionOnTenderForm positionOnTenderForm = new PositionOnTenderForm(tenderToAssignDao);
        TenderToAssign tenderToAssign = new TenderToAssign();
        Tender targetedTender = (Tender) session.getAttribute(TARGETED_TENDER_ATT);
        Long idTender = targetedTender.getId();

        try {
            tenderToAssign = positionOnTenderForm.addTenderToAssign(request, idTender);

        } catch (Exception ex) {
            Logger.getLogger(AddTender.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute(FORM_POSTION_ON_TENDER_ATT, positionOnTenderForm);
        request.setAttribute(TENDER_TO_ASSIGN_ATT, tenderToAssign);

        this.getServletContext().getRequestDispatcher(POSITION_ON_TENDER_VIEW).forward(request, response);

    }

}
