<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
=======
>>>>>>> cd425936a9d37ab614de8f70d747ddee34f68c77
package com.catnix.forms;

import com.catnix.beans.Prospect;
import com.catnix.dao.ProspectDao;
import com.catnix.dao.ProspectDaoImpl;
import java.sql.SQLException;
<<<<<<< HEAD
=======
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
>>>>>>> cd425936a9d37ab614de8f70d747ddee34f68c77
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author melanie
 */
public class ProspectForm {

<<<<<<< HEAD
    private static final String COMPANY_NAME_FIELD = "company_name";
    private static final String ACTIVITY_AREA_FIELD = "activity_area";
    private static final String WEBSITE_FIELD = "website";
    private static final String PHONE_NUMBER1_FIELD = "phone_number1";
    private static final String PHONE_NUMBER2_FIELD = "phone_number2";
    private static final String EMAIL_FIELD = "email";
    private static final String CONTACT_NAME_FIELD = "contact_name";
=======
    private static final String PROSPECT_ID = "prospectid";
    private static final String COMPANY_NAME_FIELD = "company_name";
    private static final String ACTIVITY_AREA_FIELD = "activity_area";
    private static final String WEBSITE_FIELD = "website";
    private static final String PHONE_NUMBER_FIELD = "phone_number";
    private static final String EMAIL_FIELD = "email";
    private static final String CONTACT_NAME_FIELD = "contact_name";
    private static final String STATE_FIELD = "state";
    private static final String CALLBACK_DATE_FIELD = "callback_date";
>>>>>>> cd425936a9d37ab614de8f70d747ddee34f68c77

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

<<<<<<< HEAD
        String company_name = getFieldValue(request, COMPANY_NAME_FIELD);
        String activity_area = getFieldValue(request, ACTIVITY_AREA_FIELD);
        String website = getFieldValue(request, WEBSITE_FIELD);
        String phone_number1 = getFieldValue(request, PHONE_NUMBER1_FIELD);
        String phone_number2 = getFieldValue(request, PHONE_NUMBER2_FIELD);
=======
        request.setCharacterEncoding("UTF-8");
        String company_name = getFieldValue(request, COMPANY_NAME_FIELD);
        String activity_area = getFieldValue(request, ACTIVITY_AREA_FIELD);
        String website = getFieldValue(request, WEBSITE_FIELD);
        String phone_number = getFieldValue(request, PHONE_NUMBER_FIELD);
>>>>>>> cd425936a9d37ab614de8f70d747ddee34f68c77
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
<<<<<<< HEAD
            IsNullFieldValidation(phone_number1);
            LenghtFieldValidation(phone_number1, 15);
        } catch (Exception e) {
            setErreur(PHONE_NUMBER1_FIELD, e.getMessage());
        }
        prospect.setPhone_number1(phone_number1);
        prospect.setPhone_number2(phone_number2);
=======
            IsNullFieldValidation(phone_number);
            LenghtFieldValidation(phone_number, 15);
        } catch (Exception e) {
            setErreur(PHONE_NUMBER_FIELD, e.getMessage());
        }
        prospect.setPhone_number(phone_number);
>>>>>>> cd425936a9d37ab614de8f70d747ddee34f68c77
        prospect.setEmail(email);
        prospect.setContact_name(contact_name);

        prospect.setState("new");
<<<<<<< HEAD
        prospect.setCp_id(null);
=======
>>>>>>> cd425936a9d37ab614de8f70d747ddee34f68c77
        prospect.setCallback_date(null);

        if (errors.isEmpty()) {
            prospectDao.create(prospect);
<<<<<<< HEAD
            result = "Prospect " +company_name + " has been added to the database";
=======
            result = "Prospect " + company_name + " has been added to the database";
>>>>>>> cd425936a9d37ab614de8f70d747ddee34f68c77
        } else {
            result = "Adding failed !";
        }

        return prospect;
    }

<<<<<<< HEAD
=======
    public Prospect updateProspect(HttpServletRequest request) throws SQLException, Exception {
        request.setCharacterEncoding("UTF-8");
        long prospectid = Long.parseLong(getFieldValue(request, PROSPECT_ID));
        String activity_area = getFieldValue(request, ACTIVITY_AREA_FIELD);
        String website = getFieldValue(request, WEBSITE_FIELD);
        String phone_number = getFieldValue(request, PHONE_NUMBER_FIELD);
        String email = getFieldValue(request, EMAIL_FIELD);
        String contact_name = getFieldValue(request, CONTACT_NAME_FIELD);
        String state = getFieldValue(request, STATE_FIELD);
        Date callback_date = getDateFieldValue(request, CALLBACK_DATE_FIELD);

        if (null != state) {
            switch (state) {
                case "Non interessé":

                    prospectDao.delete(prospectid);
                    break;
                case "Rendez-vous pris":
                    //            créer un client
                    prospectDao.delete(prospectid);
                    break;
                case "A rappeler":
                case "Echec de l'appel":
                    try {
                        IsNullFieldValidation(activity_area);
                    } catch (Exception e) {
                        setErreur(ACTIVITY_AREA_FIELD, e.getMessage());
                    }
                    prospect.setActivity_area(activity_area);
                    prospect.setWebsite(website);
                    try {
                        IsNullFieldValidation(phone_number);
                        LenghtFieldValidation(phone_number, 25);
                    } catch (Exception e) {
                        setErreur(PHONE_NUMBER_FIELD, e.getMessage());
                    }
                    prospect.setPhone_number(phone_number);
                    prospect.setEmail(email);
                    prospect.setContact_name(contact_name);
                    prospect.setState(state);

                    prospect.setCallback_date(callback_date);
                    prospectDao.update(prospect);
                    break;
                default:
                    break;
            }
        }

        return prospect;
    }

>>>>>>> cd425936a9d37ab614de8f70d747ddee34f68c77
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
<<<<<<< HEAD
=======

    public Date getDateFieldValue(HttpServletRequest request, String fieldName) throws ParseException {
        String value = request.getParameter(fieldName);
        if (value == null || value.trim().length() == 0) {
            return null;
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date callback_date = sdf.parse(value);
            return callback_date;
        }

    }

>>>>>>> cd425936a9d37ab614de8f70d747ddee34f68c77
}
