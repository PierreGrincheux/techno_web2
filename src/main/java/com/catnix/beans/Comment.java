/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.catnix.beans;

import java.util.Date;

/**
 *
 * @author mélanie
 */
public class Comment {
    private long id;
    private long author_id;
    private long prospect_id;
    private String content;
    private Date date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(long author_id) {
        this.author_id = author_id;
    }

    public long getProspect_id() {
        return prospect_id;
    }

    public void setProspect_id(long prospect_id) {
        this.prospect_id = prospect_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    @Override
    public String toString() {
        return "Prospect{ " + "author id = " + getAuthor_id()
                + ", prospect id = " + getProspect_id()
                + ", date =" + getDate()
                + ", content = " + getContent()
                + '}';
    }
    
}
