/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.catnix.beans;

import java.util.Date;

/**
 *
 * @author melanie
 */
public class Prospect {
<<<<<<< HEAD
=======

>>>>>>> cd425936a9d37ab614de8f70d747ddee34f68c77
    private Long id;
    private String company_name;
    private String activity_area;
    private String website;
<<<<<<< HEAD
    private String phone_number1;
    private String phone_number2;
    private String contact_name;
    private String email;
    private Long cp_id;
    private String state;
    private Date callback_date;
    

    /**
     * @return the id
     */
=======
    private String phone_number;
    private String contact_name;
    private String email;
    private String state;
    private Date callback_date;

>>>>>>> cd425936a9d37ab614de8f70d747ddee34f68c77
    public Long getId() {
        return id;
    }

<<<<<<< HEAD
    /**
     * @param id the id to set
     */
=======
>>>>>>> cd425936a9d37ab614de8f70d747ddee34f68c77
    public void setId(Long id) {
        this.id = id;
    }

<<<<<<< HEAD
    /**
     * @return the company_name
     */
=======
>>>>>>> cd425936a9d37ab614de8f70d747ddee34f68c77
    public String getCompany_name() {
        return company_name;
    }

<<<<<<< HEAD
    /**
     * @param company_name the company_name to set
     */
=======
>>>>>>> cd425936a9d37ab614de8f70d747ddee34f68c77
    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

<<<<<<< HEAD
    /**
     * @return the activity_area
     */
=======
>>>>>>> cd425936a9d37ab614de8f70d747ddee34f68c77
    public String getActivity_area() {
        return activity_area;
    }

<<<<<<< HEAD
    /**
     * @param activity_area the activity_area to set
     */
=======
>>>>>>> cd425936a9d37ab614de8f70d747ddee34f68c77
    public void setActivity_area(String activity_area) {
        this.activity_area = activity_area;
    }

<<<<<<< HEAD
    /**
     * @return the phone_number1
     */
    public String getPhone_number1() {
        return phone_number1;
    }

    /**
     * @param phone_number1 the phone_number1 to set
     */
    public void setPhone_number1(String phone_number1) {
        this.phone_number1 = phone_number1;
    }

    /**
     * @return the phone_number2
     */
    public String getPhone_number2() {
        return phone_number2;
    }

    /**
     * @param phone_number2 the phone_number2 to set
     */
    public void setPhone_number2(String phone_number2) {
        this.phone_number2 = phone_number2;
    }

    /**
     * @return the email
     */
=======
    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number1) {
        this.phone_number = phone_number1;
    }

>>>>>>> cd425936a9d37ab614de8f70d747ddee34f68c77
    public String getEmail() {
        return email;
    }

<<<<<<< HEAD
    /**
     * @param email the email to set
     */
=======
>>>>>>> cd425936a9d37ab614de8f70d747ddee34f68c77
    public void setEmail(String email) {
        this.email = email;
    }

<<<<<<< HEAD
    /**
     * @return the cp_id
     */
    public Long getCp_id() {
        return cp_id;
    }

    /**
     * @param cp_id the cp_id to set
     */
    public void setCp_id(Long cp_id) {
        this.cp_id = cp_id;
    }

    /**
     * @return the state
     */
=======
>>>>>>> cd425936a9d37ab614de8f70d747ddee34f68c77
    public String getState() {
        return state;
    }

<<<<<<< HEAD
    /**
     * @param state the state to set
     */
=======
>>>>>>> cd425936a9d37ab614de8f70d747ddee34f68c77
    public void setState(String state) {
        this.state = state;
    }

<<<<<<< HEAD
    /**
     * @return the callback_date
     */
=======
>>>>>>> cd425936a9d37ab614de8f70d747ddee34f68c77
    public Date getCallback_date() {
        return callback_date;
    }

<<<<<<< HEAD
    /**
     * @param callback_date the callback_date to set
     */
=======
>>>>>>> cd425936a9d37ab614de8f70d747ddee34f68c77
    public void setCallback_date(Date callback_date) {
        this.callback_date = callback_date;
    }

<<<<<<< HEAD
    @Override
    public String toString() {
        return "Prospect{ " + "company name =" + getCompany_name() 
                + ", activity area =" + getActivity_area()
                + ", website =" + getWebsite()
                + ", phone number 1 = " + getPhone_number1()
                + ", phone number 2 = " + getPhone_number2()
                + ", contact name = " + getContact_name()
                + ", email = " + getEmail()
                + ", cp_id = " + getCp_id()
                + ", state = " + getState()
                + ", callback date = " + getCallback_date()
                +'}';
    }

    /**
     * @return the website
     */
=======
    

>>>>>>> cd425936a9d37ab614de8f70d747ddee34f68c77
    public String getWebsite() {
        return website;
    }

<<<<<<< HEAD
    /**
     * @param website the website to set
     */
=======
>>>>>>> cd425936a9d37ab614de8f70d747ddee34f68c77
    public void setWebsite(String website) {
        this.website = website;
    }

<<<<<<< HEAD
    /**
     * @return the contact_name
     */
=======
>>>>>>> cd425936a9d37ab614de8f70d747ddee34f68c77
    public String getContact_name() {
        return contact_name;
    }

<<<<<<< HEAD
    /**
     * @param contact_name the contact_name to set
     */
    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }
}

=======
    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }
    
    @Override
    public String toString() {
        return "Prospect{ " + "company name =" + getCompany_name()
                + ", activity area =" + getActivity_area()
                + ", website =" + getWebsite()
                + ", phone number = " + getPhone_number()
                + ", contact name = " + getContact_name()
                + ", email = " + getEmail()
                + ", state = " + getState()
                + ", callback date = " + getCallback_date()
                + '}';
    }
}
>>>>>>> cd425936a9d37ab614de8f70d747ddee34f68c77
