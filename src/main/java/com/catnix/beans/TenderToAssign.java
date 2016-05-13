/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.catnix.beans;

import java.util.Date;

/**
 *
 * @author Fritsch
 */
public class TenderToAssign {

    private Long id;
    private Long id_tender;
    private String title;
    private String nameCP;
    private String motivationText;
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_tender() {
        return id_tender;
    }

    public void setId_tender(Long id_tender) {
        this.id_tender = id_tender;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNameCP() {
        return nameCP;
    }

    public void setNameCP(String nameCP) {
        this.nameCP = nameCP;
    }

    public String getMotivationText() {
        return motivationText;
    }

    public void setMotivationText(String motivationText) {
        this.motivationText = motivationText;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
