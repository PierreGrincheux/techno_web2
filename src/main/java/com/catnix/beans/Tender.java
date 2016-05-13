/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.catnix.beans;

import java.io.Serializable;

/**
 *
 * @author Fritsch
 */
public class Tender implements Serializable {

    private static final Long serialVersionUID = 1L;
    private Long id;
    private String nameCp;
    private String title;
    private String activityArea;
    private String contactName;
    private String contactPhone;
    private String contactEmail;
    private String origin;
    private String accepted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameCp() {
        return nameCp;
    }

    public void setNameCp(String nameCp) {
        this.nameCp = nameCp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getActivityArea() {
        return activityArea;
    }

    public void setActivityArea(String activityArea) {
        this.activityArea = activityArea;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getAccepted() {
        return accepted;
    }

    public void setAccepted(String accepted) {
        this.accepted = accepted;
    }

    @Override
    public String toString() {
        return "Tender{" + "id=" + id + ", title=" + title + ", activityArea=" + activityArea + ", contactName=" + contactName + ", contactPhone=" + contactPhone + ", contactEmail=" + contactEmail + ", origin=" + origin + '}';
    }

}
