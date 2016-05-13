/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.catnix.servlets;

import com.catnix.beans.Tender;
import com.catnix.dao.DAOFactory;
import com.catnix.dao.TenderDao;
import com.catnix.forms.TenderForm;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Fritsch
 */
@WebServlet(name = "AddTender", urlPatterns = "/addTender")
public class AddTender extends HttpServlet {

    private static final String ADD_TENDER_VIEW = "/WEB-INF/addTender.jsp";
    private static final String FORM_TENDER_ATT = "formTender";
    private static final String TENDER_ATT = "tender";
    private static final String DAO_FACTORY_CONF = "daofactory";

    private TenderDao tenderDao;

    @Override
    public void init() throws ServletException {

        this.tenderDao = ((DAOFactory) getServletContext().getAttribute(DAO_FACTORY_CONF)).getTenderDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher(ADD_TENDER_VIEW).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        TenderForm tenderForm = new TenderForm(tenderDao);
        Tender tender = new Tender();

        try {
            tender = tenderForm.addTender(request);

        } catch (Exception ex) {
            Logger.getLogger(AddTender.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute(FORM_TENDER_ATT, tenderForm);
        request.setAttribute(TENDER_ATT, tender);

        this.getServletContext().getRequestDispatcher(ADD_TENDER_VIEW).forward(request, response);

    }

}
