package com.castro.classificadorhumor.models;

import java.util.Date;
import java.util.List;

public class Ticket {
    private Long ticketId;
    private Long categoryId;
    private Long customerId;
    private String customerName;
    private String customerEmail;
    private Date dateCreate;
    private Date dateUpdate;
    private List<Interaction> interactions;

    public Ticket(Long ticketId, Long categoryId, Long customerId, String customerName, String customerEmail, Date dateCreate, Date dateUpdate, List<Interaction> interactions) {
        this.ticketId = ticketId;
        this.categoryId = categoryId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.dateCreate = dateCreate;
        this.dateUpdate = dateUpdate;
        this.interactions = interactions;
    }

    public Ticket() {
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public List<Interaction> getInteractions() {
        return interactions;
    }

    public void setInteractions(List<Interaction> interactions) {
        this.interactions = interactions;
    }
}
