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
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author melanie
 */
public class ProspectForm {

    private static final String PROSPECT_ID = "prospectid";
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

        request.setCharacterEncoding("UTF-8");
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
        request.setCharacterEncoding("UTF-8");
        long prospectid = Long.parseLong(getFieldValue(request, PROSPECT_ID));
        String activity_area = getFieldValue(request, ACTIVITY_AREA_FIELD);
        String website = getFieldValue(request, WEBSITE_FIELD);
        String phone_number = getFieldValue(request, PHONE_NUMBER_FIELD);
        String email = getFieldValue(request, EMAIL_FIELD);
        String contact_name = getFieldValue(request, CONTACT_NAME_FIELD);
        String state = getFieldValue(request, STATE_FIELD);
        String scallback_date = getFieldValue(request, CALLBACK_DATE_FIELD);

        if (null != state) {
            if ("Non interessé".equals(state)) {
                prospectDao.delete(prospectid);
                if (errors.isEmpty()) {
                    result = "Prospect has been successfully deleted";
                } else {
                    result = "delete failed !";
                }
            } else if ("Rendez-vous pris".equals(state)) {
                try {
                    IsNullFieldValidation(activity_area);
                } catch (Exception e) {
                    setErreur(ACTIVITY_AREA_FIELD, e.getMessage());
                }
                prospect.setId(prospectid);
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
                prospect.setCallback_date(null);
                prospectDao.update(prospect);

                if (errors.isEmpty()) {
                    result = "Prospect has been successfully updated";
                } else {
                    result = "update failed !";
                }

            } else if ("A rappeler".equals(state)) {
                try {
                    IsNullFieldValidation(activity_area);
                } catch (Exception e) {
                    setErreur(ACTIVITY_AREA_FIELD, e.getMessage());
                }
                prospect.setId(prospectid);
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
                try {
                    IsNullFieldValidation(scallback_date);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date callback_date = sdf.parse(scallback_date);
                    prospect.setCallback_date(callback_date);

                } catch (Exception e) {
                    setErreur(CALLBACK_DATE_FIELD, e.getMessage());
                }
                               
                prospectDao.update(prospect);

                if (errors.isEmpty()) {
                    result = "Prospect has been successfully updated";
                } else {
                    result = "update failed !";
                }

            }
        }

        return prospect;
    }

    public void IsNullFieldValidation(String field) throws SQLException, Exception {
        if (field == null) {
            throw new Exception("Please fill in the field.");
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
