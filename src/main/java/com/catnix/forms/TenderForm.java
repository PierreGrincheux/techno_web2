/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.catnix.forms;

import com.catnix.beans.Tender;
import com.catnix.dao.TenderDao;
import com.catnix.methods.Methods;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Fritsch
 */
public class TenderForm {

    private static final String TITLE_FIELD = "title";
    private static final String ACTIVITY_AREA_FIELD = "activityArea";
    private static final String CONTACT_NAME_FIELD = "contactName";
    private static final String CONTACT_PHONE_FIELD = "contactPhone";
    private static final String CONTACT_EMAIL_FIELD = "contactEmail";
    private static final String ORIGIN_FIELD = "origin";

    private String result;

    private Map<String, String> errors = new HashMap<>();

    public String getResult() {
        return result;

    }

    public Map<String, String> getErrors() {
        return errors;
    }

    private TenderDao tenderDao;

    public TenderForm(TenderDao tenderDao) {

        this.tenderDao = tenderDao;
    }

    public Tender addTender(HttpServletRequest request) throws SQLException, Exception {

        request.setCharacterEncoding("UTF-8");
        String title = Methods.getFieldValue(request, TITLE_FIELD);
        String activityArea = Methods.getFieldValue(request, ACTIVITY_AREA_FIELD);
        String contactName = Methods.getFieldValue(request, CONTACT_NAME_FIELD);
        String contactPhone = Methods.getFieldValue(request, CONTACT_PHONE_FIELD);
        String contactEmail = Methods.getFieldValue(request, CONTACT_EMAIL_FIELD);
        String origin = Methods.getFieldValue(request, ORIGIN_FIELD);

        Tender tender = new Tender();

        handleTitle(title, tender);
        handleActivityArea(activityArea, tender);
        handleContactName(contactName, tender);
        handleContactPhone(contactPhone, tender);
        handleContactEmail(contactEmail, tender);
        handleOrigin(origin, tender);

        if (errors.isEmpty()) {
            tenderDao.create(tender);
            result = "Ajouté avec succès ! ";
        } else {
            result = "L'ajout à échoué !";
        }

        return tender;
    }

    private void handleTitle(String title, Tender tender) {
        try {
            titleValidation(title);
        } catch (Exception e) {
            setErreur(TITLE_FIELD, e.getMessage());
        }
        tender.setTitle(title);
    }

    private void handleActivityArea(String activityArea, Tender tender) {
        try {
            generalFieldValidation(activityArea);
        } catch (Exception e) {
            setErreur(ACTIVITY_AREA_FIELD, e.getMessage());
        }
        tender.setActivityArea(activityArea);
    }

    private void handleContactName(String contactName, Tender tender) {
        try {
            generalFieldValidation(contactName);
        } catch (Exception e) {
            setErreur(CONTACT_NAME_FIELD, e.getMessage());
        }
        tender.setContactName(contactName);
    }

    private void handleContactPhone(String contactPhone, Tender tender) {
        try {
            contactPhoneNumberValidation(contactPhone);
        } catch (Exception e) {
            setErreur(CONTACT_PHONE_FIELD, e.getMessage());
        }
        tender.setContactPhone(contactPhone);
    }

    private void handleContactEmail(String contactEmail, Tender tender) {
        tender.setContactEmail(contactEmail);
    }

    private void handleOrigin(String origin, Tender tender) {
        try {
            generalFieldValidation(origin);
        } catch (Exception e) {
            setErreur(ORIGIN_FIELD, e.getMessage());
        }
        tender.setOrigin(origin);
    }

    public void generalFieldValidation(String field) throws SQLException, Exception {

        if (field != null) {

            if (field.length() < 4) {

                throw new Exception("Ce champ doit contenir au moins 4 charactères.");
            }

        } else {

            throw new Exception("Ce champ ne peut être vide.");
        }
    }

    public void titleValidation(String title) throws SQLException, Exception {

        if (title != null) {

            if (title.length() >= 5) {

                if (tenderDao.find(title) != null) {

                    throw new Exception("Un appel d'offre ayant ce titre existe déjà, merci d'en saisir un nouveau.");
                }

            } else {

                throw new Exception("Le titre doit contenir au moins 5 charactères.");

            }

        } else {

            throw new Exception("Ce champ ne peut être vide.");
        }

    }
    
      public void contactPhoneNumberValidation(String contactPhone) throws SQLException, Exception {

        if (contactPhone != null) {

            if (contactPhone.length() < 10 || contactPhone.length() > 10 ) {

                throw new Exception("Ce champ doit contenir 10 charactères.");
            }

        } else {

            throw new Exception("Ce champ ne peut être vide.");
        }
    }

    public void setErreur(String field, String message) {
        errors.put(field, message);
    }

}
