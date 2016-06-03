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
    private String phoneNumber;
    private String contact_name;
    private String email;
    private String state;
    private Date callback_date;
 
    @Override
    public String toString() {
        return "Prospect{ " + "company name =" + getCompany_name()
                + ", activity area =" + getActivity_area()
                + ", website =" + getWebsite()
                + ", phone number = " + getPhoneNumber()
                + ", contact name = " + getContact_name()
                + ", email = " + getEmail()
                + ", state = " + getState()
                + ", callback date = " + getCallback_date()
                + '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getActivity_area() {
        return activity_area;
    }

    public void setActivity_area(String activity_area) {
        this.activity_area = activity_area;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getCallback_date() {
        return callback_date;
    }

    public void setCallback_date(Date callback_date) {
        this.callback_date = callback_date;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

