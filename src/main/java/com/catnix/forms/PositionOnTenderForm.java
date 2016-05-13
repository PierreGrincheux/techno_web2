/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.catnix.forms;

import com.catnix.beans.TenderToAssign;
import com.catnix.dao.TenderToAssignDao;
import com.catnix.methods.Methods;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Fritsch
 */
public class PositionOnTenderForm {

    private static final String TITLE_FIELD = "title";
    private static final String NAME_CP_FIELD = "nameCP";
    private static final String MOTIVATION_TEXT_FIELD = "motivationText";

    private String result;

    private Map<String, String> errors = new HashMap<>();

    public String getResult() {
        return result;

    }

    public Map<String, String> getErrors() {
        return errors;
    }

    private TenderToAssignDao tenderToAssignDao;

    public PositionOnTenderForm(TenderToAssignDao tenderToAssignDao) {

        this.tenderToAssignDao = tenderToAssignDao;
    }

    public TenderToAssign addTenderToAssign(HttpServletRequest request, Long id_tender) throws SQLException, Exception {

        request.setCharacterEncoding("UTF-8");
        String title = Methods.getFieldValue(request, TITLE_FIELD);
        String nameCP = Methods.getFieldValue(request, NAME_CP_FIELD);
        String motivationText = Methods.getFieldValue(request, MOTIVATION_TEXT_FIELD);

        TenderToAssign tenderToAssign = new TenderToAssign();

        tenderToAssign.setId_tender(id_tender);
        handleTitle(title, tenderToAssign);
        handleNameCp(tenderToAssign.getId_tender(), nameCP, tenderToAssign);
        handleMotivationText(motivationText, tenderToAssign);

        if (errors.isEmpty()) {
            tenderToAssignDao.create(tenderToAssign);
            result = "Positioning succeeds !";
        } else {
            result = "Positioning failed !";
        }

        return tenderToAssign;
    }

    private void handleTitle(String title, TenderToAssign tenderToAssign) {
        try {
            titleValidation(title);
        } catch (Exception e) {
            setErreur(TITLE_FIELD, e.getMessage());
        }
        tenderToAssign.setTitle(title);
    }

    public void titleValidation(String title) throws SQLException, Exception {

        if (title != null) {

            if (title.length() < 5) {

                throw new Exception("Title must be at least 5 characters long.");
            }

        } else {

            throw new Exception("Please fill the field.");
        }

    }

    private void handleNameCp(Long idTender, String nameCP, TenderToAssign tenderToAssign) {
        try {
            nameCpValidation(idTender, nameCP);
        } catch (Exception e) {
            setErreur(NAME_CP_FIELD, e.getMessage());
        }
        tenderToAssign.setNameCP(nameCP);
    }

    public void nameCpValidation(Long idTender, String nameCP) throws SQLException, Exception {

        if (nameCP != null) {

            if (nameCP.length() >= 6) {

                if (tenderToAssignDao.find(idTender, nameCP) != null) {

                    throw new Exception("You are already positioned on this tender.");
                }

            } else {

                throw new Exception("Name must be at least 6 characters long.");

            }

        } else {

            throw new Exception("Please fill the field.");
        }

    }

    private void handleMotivationText(String motivationText, TenderToAssign tenderToAssign) {
        try {
            motivationTextValidation(motivationText);
        } catch (Exception e) {
            setErreur(MOTIVATION_TEXT_FIELD, e.getMessage());
        }
        tenderToAssign.setMotivationText(motivationText);
    }

    public void motivationTextValidation(String motivationText) throws SQLException, Exception {

        if (motivationText != null) {

            if (motivationText.length() < 25) {

                throw new Exception("Motivation text must be at least 25 characters long.");
            }

        } else {

            throw new Exception("Please fill the field.");
        }

    }

    public void setErreur(String field, String message) {
        errors.put(field, message);
    }

}
