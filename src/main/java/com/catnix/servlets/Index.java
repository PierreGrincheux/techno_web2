/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.catnix.servlets;

import com.catnix.dao.ProspectDao;
import com.catnix.dao.ProspectDaoImpl;
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
@WebServlet(name = "Index", urlPatterns = "/index")
public class Index extends HttpServlet {

    private static final String INDEX_VIEW = "/WEB-INF/index.jsp";
    private static final String NB_NEW_PROSPECTS = "nbNewProspects";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        ProspectDao prospectDao = new ProspectDaoImpl();
        int nbNewProspects = prospectDao.getNbOfProspectByState("new");
        
        request.setAttribute(NB_NEW_PROSPECTS, nbNewProspects);
        this.getServletContext().getRequestDispatcher(INDEX_VIEW).forward(request, response);
    }

}
