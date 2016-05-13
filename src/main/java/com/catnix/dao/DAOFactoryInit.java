/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.catnix.dao;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author Fritsch
 */
@WebListener
public class DAOFactoryInit implements ServletContextListener {

    private static final String ATT_DAO_FACTORY = "daofactory";

    private DAOFactory daoFactory;

    @Override
    public void contextInitialized(ServletContextEvent event) {

        ServletContext servletContext = event.getServletContext();

        this.daoFactory = DAOFactory.getInstance();

        servletContext.setAttribute(ATT_DAO_FACTORY, this.daoFactory);
        System.out.println("The application started");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
          System.out.println("The application stopped");
    }
}
