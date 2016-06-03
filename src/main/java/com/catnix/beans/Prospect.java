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
    private Long id;
    private String company_name;
    private String activity_area;
    private String website;
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
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the company_name
     */
    public String getCompany_name() {
        return company_name;
    }

    /**
     * @param company_name the company_name to set
     */
    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    /**
     * @return the activity_area
     */
    public String getActivity_area() {
        return activity_area;
    }

    /**
     * @param activity_area the activity_area to set
     */
    public void setActivity_area(String activity_area) {
        this.activity_area = activity_area;
    }

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
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

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
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the callback_date
     */
    public Date getCallback_date() {
        return callback_date;
    }

    /**
     * @param callback_date the callback_date to set
     */
    public void setCallback_date(Date callback_date) {
        this.callback_date = callback_date;
    }

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
    public String getWebsite() {
        return website;
    }

    /**
     * @param website the website to set
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * @return the contact_name
     */
    public String getContact_name() {
        return contact_name;
    }

    /**
     * @param contact_name the contact_name to set
     */
    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }
}

