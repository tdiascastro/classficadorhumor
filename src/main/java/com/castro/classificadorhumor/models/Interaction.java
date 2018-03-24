package com.castro.classificadorhumor.models;

import java.util.Date;

public class Interaction {
    private String subject;
    private String message;
    private Date dateCreate;
    private String sender;

    public Interaction(String subject, String message, Date dateCreate, String sender) {
        this.subject = subject;
        this.message = message;
        this.dateCreate = dateCreate;
        this.sender = sender;
    }

    public Interaction() {
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
