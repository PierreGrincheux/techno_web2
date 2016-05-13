/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.catnix.servlets;

import com.catnix.beans.Member;
import com.catnix.dao.DAOFactory;
import com.catnix.dao.MemberDao;
import com.catnix.forms.ConnectionForm;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
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
@WebServlet(name = "Connection", urlPatterns = "/connection")
public class Connection extends HttpServlet {

    private static final String MEMBER_ATT = "member";
    private static final String FORM_ATT = "form";

    private static final String CONNECTION_FORM_VIEW = "/WEB-INF/connection.jsp";
    private static final String INDEX_RELATIVE_URL = "/index";

    private static final String DAO_FACTORY_INIT = "daofactory";

    private MemberDao memberDao;

    @Override
    public void init() throws ServletException {

        this.memberDao = ((DAOFactory) getServletContext().getAttribute(DAO_FACTORY_INIT)).getMemberDao();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher(CONNECTION_FORM_VIEW).forward(request, response);

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ConnectionForm connectionForm = new ConnectionForm(memberDao);

        Member member = new Member();
        try {
            member = connectionForm.connectMember(request);
        } catch (SQLException | UnsupportedEncodingException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }

        HttpSession session = request.getSession();

        if (connectionForm.getErrors().isEmpty()) {

            session.setAttribute(MEMBER_ATT, member);
            request.setAttribute(FORM_ATT, connectionForm);

            response.sendRedirect(request.getContextPath() + INDEX_RELATIVE_URL);

        } else {
            request.setAttribute(FORM_ATT, connectionForm);
            request.setAttribute(MEMBER_ATT, member);

            this.getServletContext().getRequestDispatcher(CONNECTION_FORM_VIEW).forward(request, response);
        }

    }

}
