package com.catnix.forms;

import com.catnix.beans.Prospect;
import com.catnix.dao.ProspectDao;
import com.catnix.dao.ProspectDaoImpl;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author melanie
 */
public class ProspectForm {

    private static final String PROSPECT_ID = "propect_id";
    private static final String COMPANY_NAME_FIELD = "company_name";
    private static final String ACTIVITY_AREA_FIELD = "activity_area";
    private static final String WEBSITE_FIELD = "website";
    private static final String PHONE_NUMBER_FIELD = "phone_number";
    private static final String EMAIL_FIELD = "email";
    private static final String CONTACT_NAME_FIELD = "contact_name";
    private static final String STATE_FIELD = "state";
    private static final String CALLBACK_DATE_FIELD = "callback_date";

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
        String phone_number = getFieldValue(request, PHONE_NUMBER_FIELD);
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
            IsNullFieldValidation(phone_number);
            LenghtFieldValidation(phone_number, 15);
        } catch (Exception e) {
            setErreur(PHONE_NUMBER_FIELD, e.getMessage());
        }
        prospect.setPhone_number(phone_number);
        prospect.setEmail(email);
        prospect.setContact_name(contact_name);

        prospect.setState("new");
        prospect.setCallback_date(null);

        if (errors.isEmpty()) {
            prospectDao.create(prospect);
            result = "Prospect " + company_name + " has been added to the database";
        } else {
            result = "Adding failed !";
        }

        return prospect;
    }

    public Prospect updateProspect(HttpServletRequest request) throws SQLException, Exception {
        long prospectid = Long.parseLong(getFieldValue(request, PROSPECT_ID));
        String activity_area = getFieldValue(request, ACTIVITY_AREA_FIELD);
        String website = getFieldValue(request, WEBSITE_FIELD);
        String phone_number = getFieldValue(request, PHONE_NUMBER_FIELD);
        String email = getFieldValue(request, EMAIL_FIELD);
        String contact_name = getFieldValue(request, CONTACT_NAME_FIELD);
        String state = getFieldValue(request, STATE_FIELD);
        Date callback_date = getDateFieldValue(request, CALLBACK_DATE_FIELD);

        if (null != state) switch (state) {
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
                }   prospect.setActivity_area(activity_area);
                prospect.setWebsite(website);
                try {
                    IsNullFieldValidation(phone_number);
                    LenghtFieldValidation(phone_number, 25);
                } catch (Exception e) {
                    setErreur(PHONE_NUMBER_FIELD, e.getMessage());
                }   prospect.setPhone_number(phone_number);
                prospect.setEmail(email);
                prospect.setContact_name(contact_name);
                prospect.setState(state);
                
                prospect.setCallback_date(callback_date);
                prospectDao.update(prospect);
                break;
            default:
                break;
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

}
