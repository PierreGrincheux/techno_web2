/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.catnix.servlets;

import com.catnix.beans.Prospect;
import com.catnix.forms.ProspectForm;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mélanie
 */
@WebServlet(name = "AddProspect", urlPatterns = {"/AddProspect"})
public class AddProspect extends HttpServlet {
    public static final String VIEW_ADD_PROSPECT = "/WEB-INF/addProspect.jsp";
    public static final String ATT_FORM_PROSPECT = "formprospect";
    public static final String ATT_PROSPECT = "prospect";


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(VIEW_ADD_PROSPECT).forward(request, response);
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProspectForm prospectForm = new ProspectForm();
        Prospect prospect = new Prospect();

        try {
            prospect = prospectForm.addProspect(request);

        } catch (Exception ex) {
            Logger.getLogger(AddProspect.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute(ATT_FORM_PROSPECT, prospectForm);
        request.setAttribute(ATT_PROSPECT, prospect);

        this.getServletContext().getRequestDispatcher(VIEW_ADD_PROSPECT).forward(request, response);

       
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
