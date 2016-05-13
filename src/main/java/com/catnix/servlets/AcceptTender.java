/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.catnix.servlets;

import com.catnix.beans.Tender;
import com.catnix.dao.DAOFactory;
import com.catnix.dao.TenderDao;
import com.catnix.exceptions.DAOException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Fritsch
 */
@WebServlet(name = "AcceptTender", urlPatterns = "/acceptTender")
public class AcceptTender extends HttpServlet {

    private static final String DAO_FACTORY_CONF = "daofactory";
    private static final String ID_TENDER_PARAM = "idTender";
    private static final String HANDLE_TENDER_RELATIVE_URL = "/handleTender";

    private TenderDao tenderDao;

    @Override
    public void init() throws ServletException {

        this.tenderDao = ((DAOFactory) getServletContext().getAttribute(DAO_FACTORY_CONF)).getTenderDao();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idTender = getParameterValue(request, ID_TENDER_PARAM);
        Long id = Long.parseLong(idTender);

        Tender tender = new Tender();
        tender.setId(id);

        try {

            tenderDao.updateAcceptedTender(tender);

        } catch (DAOException e) {

            e.getMessage();
        }

        response.sendRedirect(request.getContextPath() + HANDLE_TENDER_RELATIVE_URL);
    }

    private static String getParameterValue(HttpServletRequest request, String fieldName) {
        String value = request.getParameter(fieldName);
        if (value == null || value.trim().length() == 0) {
            return null;
        } else {
            return value;
        }
    }

}
