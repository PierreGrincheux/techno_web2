package com.catnix.servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author mélanie
 */
@WebServlet(name = "AddProspectCSV", urlPatterns = {"/addProspectCSV"})
@MultipartConfig
public class AddProspectCSV extends HttpServlet {

    public static final String VIEW_ADD_PROSPECT_CSV = "/WEB-INF/addProspectCSV.jsp";
    private static final String SAVE_DIRECTORY = "src/main/resources/downloadedFiles";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(VIEW_ADD_PROSPECT_CSV).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String appPath = request.getServletContext().getRealPath("");
        if (appPath.contains("target\\Catnix-1.0-SNAPSHOT")) {
            appPath = appPath.substring(0, appPath.length() - 26);
        }
        String savePath = appPath + SAVE_DIRECTORY;

        Part filePart = request.getPart("csvfile");
        String fileName = "ajoutprospects.csv";
        filePart.write(savePath + File.separator + fileName);

        request.setAttribute("message", "le fichier a bie été téléchargé!");

        this.getServletContext().getRequestDispatcher(VIEW_ADD_PROSPECT_CSV).forward(request, response);
    }

}
