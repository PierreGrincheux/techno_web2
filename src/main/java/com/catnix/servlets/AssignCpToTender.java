/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.catnix.servlets;

import com.catnix.beans.Tender;
import com.catnix.beans.TenderToAssign;
import com.catnix.dao.DAOFactory;
import com.catnix.dao.TenderDao;
import com.catnix.dao.TenderToAssignDao;
import com.catnix.methods.Methods;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nico
 */
@WebServlet(name = "AssignCpToTender", urlPatterns = "/assignCpToTender")
public class AssignCpToTender extends HttpServlet {
    
    private static final String DAO_FACTORY_CONF = "daofactory";
    private static final String ID_TENDER_TO_ASSIGN_PARAM = "idTenderToAssign";
    private static final String ID_TENDER_PARAM = "idTender";
    private static final String NAME_CP_PARAM = "nameCp";
    private static final String CONSULT_TENDERS_TO_ASSIGN_RELATIVE_URL = "/consultTendersToAssign";
    
    private TenderDao tenderDao;
    private TenderToAssignDao tenderToAssignDao;
    
    @Override
    public void init() throws ServletException {
        
        this.tenderDao = ((DAOFactory) getServletContext().getAttribute(DAO_FACTORY_CONF)).getTenderDao();
        this.tenderToAssignDao = ((DAOFactory) getServletContext().getAttribute(DAO_FACTORY_CONF)).getTenderToAssignDao();
    }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String idTenderToAssignValue = Methods.getFieldValue(request, ID_TENDER_TO_ASSIGN_PARAM);
        String idTenderValue = Methods.getFieldValue(request, ID_TENDER_PARAM);
        String nameCp = Methods.getFieldValue(request, NAME_CP_PARAM);
        Long idTenderToAssign = Long.parseLong(idTenderToAssignValue);
        Long idTender = Long.parseLong(idTenderValue);
        
        TenderToAssign tenderToAssign = new TenderToAssign();
        tenderToAssign.setId(idTenderToAssign);
        tenderToAssign.setId_tender(idTender);
        tenderToAssign.setNameCP(nameCp);
        
        Tender tender = new Tender();
        tender.setId(tenderToAssign.getId_tender());
        tender.setNameCp(tenderToAssign.getNameCP());
        
        tenderToAssignDao.updateAcceptedTender(tenderToAssign);
        tenderDao.updateTenderWhenCpIsAssigned(tender);
        
        response.sendRedirect(request.getContextPath() + CONSULT_TENDERS_TO_ASSIGN_RELATIVE_URL);
        
    }
    
}
