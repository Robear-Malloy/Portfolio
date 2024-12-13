package com.robear.portfolio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;

@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "contact_id")
    private Long id;

    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    @Basic(optional = false)
    @Column(name = "email")
    private String email;

    @Column(name = "company")
    private String company;

    @Column(name = "description")
    private String description;

    @Basic(optional = false)
    @Column(name = "date_sent")
    private String dateSent;

    @Basic(optional = false)
    @Column(name = "reached_out")
    private Integer reachedOut;

    public Long getId() { return this.id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

    public String getEmail() { return this.email; }

    public void setEmail(String email) { this.email = email; }

    public String getCompany() { return this.company; }

    public void setCompany(String company) { this.company = company; }

    public String getDescription() { return this.description; }

    public void setDescription(String description) { this.description = description; }

    public String getDateSent() { return this.dateSent; }

    public void setDateSent(String dateSent) { this.dateSent = dateSent; }

    public Integer getReachedOut() { return this.reachedOut; }

    public void setReachedOut(Integer reachedOut) { this.reachedOut = reachedOut; }
}
