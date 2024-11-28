package com.robear.portfolio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;

@Entity
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "experience_id")
    private Long id;

    @Basic(optional = false)
    @Column(name = "company")
    private String company;

    @Basic(optional = false)
    @Column(name = "position")
    private String position;

    @Basic(optional = false)
    @Column(name = "date_started")
    private String dateStarted;

    @Column(name = "date_ended")
    private String dateEnded;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getCompany() { return company; }

    public void setCompany(String company) { this.company = company; }

    public String getPosition() { return position; }

    public void setPosition(String position) { this.position = position; }

    public String getDateStarted() { return dateStarted; }

    public void setDateStarted(String dateStarted) { this.dateStarted = dateStarted; }

    public String getDateEnded() { return dateEnded; }

    public void setDateEnded(String dateEnded) { this.dateEnded = dateEnded; }
}