/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.catnix.forms;

import com.catnix.beans.Prospect;
import com.catnix.dao.ProspectDao;
import com.catnix.dao.ProspectDaoImpl;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author melanie
 */
public class ProspectForm {

    private static final String COMPANY_NAME_FIELD = "company_name";
    private static final String ACTIVITY_AREA_FIELD = "activity_area";
    private static final String WEBSITE_FIELD = "website";
    private static final String PHONE_NUMBER1_FIELD = "phone_number1";
    private static final String PHONE_NUMBER2_FIELD = "phone_number2";
    private static final String EMAIL_FIELD = "email";
    private static final String CONTACT_NAME_FIELD = "contact_name";

    private String result;
    private Map<String, String> errors = new HashMap<>();

    public String getResult() {
        return result;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    private Prospect prospect;
    private final ProspectDao prospectDao;

    public ProspectForm() {
        this.prospectDao = new ProspectDaoImpl();
        this.prospect = new Prospect();
    }

    public Prospect addProspect(HttpServletRequest request) throws SQLException, Exception {

        String company_name = getFieldValue(request, COMPANY_NAME_FIELD);
        String activity_area = getFieldValue(request, ACTIVITY_AREA_FIELD);
        String website = getFieldValue(request, WEBSITE_FIELD);
        String phone_number1 = getFieldValue(request, PHONE_NUMBER1_FIELD);
        String phone_number2 = getFieldValue(request, PHONE_NUMBER2_FIELD);
        String email = getFieldValue(request, EMAIL_FIELD);
        String contact_name = getFieldValue(request, CONTACT_NAME_FIELD);

        try {
            IsNullFieldValidation(company_name);
        } catch (Exception e) {
            setErreur(COMPANY_NAME_FIELD, e.getMessage());
        }
        prospect.setCompany_name(company_name);

        try {
            IsNullFieldValidation(activity_area);
        } catch (Exception e) {
            setErreur(ACTIVITY_AREA_FIELD, e.getMessage());
        }
        prospect.setActivity_area(activity_area);
        prospect.setWebsite(website);

        try {
            IsNullFieldValidation(phone_number1);
            LenghtFieldValidation(phone_number1, 15);
        } catch (Exception e) {
            setErreur(PHONE_NUMBER1_FIELD, e.getMessage());
        }
        prospect.setPhone_number1(phone_number1);
        prospect.setPhone_number2(phone_number2);
        prospect.setEmail(email);
        prospect.setContact_name(contact_name);

        prospect.setState("new");
        prospect.setCp_id(null);
        prospect.setCallback_date(null);

        if (errors.isEmpty()) {
            prospectDao.create(prospect);
            result = "Prospect " +company_name + " has been added to the database";
        } else {
            result = "Adding failed !";
        }

        return prospect;
    }

    public void IsNullFieldValidation(String field) throws SQLException, Exception {
        if (field == null) {
            throw new Exception("Please fill the field.");
        }
    }

    public void LenghtFieldValidation(String field, int lenght) throws SQLException, Exception {
        if (field.length() > lenght) {
            throw new Exception("This field cannot be more than " + lenght + " characters long");

        }
    }

    public void setErreur(String field, String message) {
        errors.put(field, message);
    }

    public String getFieldValue(HttpServletRequest request, String fieldName) {
        String value = request.getParameter(fieldName);
        if (value == null || value.trim().length() == 0) {
            return null;
        } else {
            return value;
        }
    }
}
