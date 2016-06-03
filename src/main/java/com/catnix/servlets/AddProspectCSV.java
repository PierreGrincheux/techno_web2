package com.catnix.servlets;

import com.catnix.beans.Prospect;
import com.catnix.dao.ProspectDao;
import com.catnix.dao.ProspectDaoImpl;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

        // downloading the file 
        String appPath = request.getServletContext().getRealPath("");
        if (appPath.contains("target\\Catnix-1.0-SNAPSHOT")) {
            appPath = appPath.substring(0, appPath.length() - 26);
        }
        String savePath = appPath + SAVE_DIRECTORY;

        Part filePart = request.getPart("csvfile");
        String fileName = "ajoutprospects.csv";
        filePart.write(savePath + File.separator + fileName);

        String UPLOAD_SUCCESSFULL = "le fichier a bien été téléchargé!";

        // reading the file
        String csvFile = savePath + "/" + fileName;
        String cvsSplitBy = ",";
        ProspectDao prospectDao = new ProspectDaoImpl();
        String result = "";
        String error = "";
        int prospectsNotAdded = 0;
        int prospectsAdded = 0;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(csvFile), "UTF8"));
        //BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFile));
        String csvline="";
        while ((csvline = bufferedReader.readLine()) != null) {
            //adding a prospect
            String[] csvprospect = csvline.split(cvsSplitBy);
            if ("".equals(csvprospect[0]) || "".equals(csvprospect[1]) || "".equals(csvprospect[3])) {
                prospectsNotAdded++;
            } else {
                Prospect prospect = new Prospect();
                prospect.setCompany_name(csvprospect[0]);
                prospect.setActivity_area(csvprospect[1]);
                prospect.setWebsite(csvprospect[2]);
                prospect.setPhoneNumber(csvprospect[3]);
                prospect.setEmail(csvprospect[4]);
                prospect.setContact_name(csvprospect[5]);
                prospect.setState("new");
                prospect.setCallback_date(null);

                prospectDao.create(prospect);
                prospectsAdded++;
            }
        }
        result = prospectsAdded + " prospects ont étés ajoutés à la base de données";
        if (prospectsNotAdded > 0) {
            error = prospectsNotAdded + " prospects n'ont pas étés ajoutés à la base de données par manque d'information les concernant";
        }

        request.setAttribute("result", result);
        request.setAttribute("error", error);
        request.setAttribute("upload", UPLOAD_SUCCESSFULL);

        this.getServletContext().getRequestDispatcher(VIEW_ADD_PROSPECT_CSV).forward(request, response);
    }

}
