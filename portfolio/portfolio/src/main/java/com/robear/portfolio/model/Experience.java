package com.robear.portfolio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;

import java.time.LocalDate;

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
    private LocalDate dateStarted;

    @Column(name = "date_ended")
    private LocalDate dateEnded;

    @Basic(optional = false)
    @Column(name = "is_featured")
    private Boolean isFeatured;

    @Basic(optional = false)
    @Column(name = "language")
    private String language;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getCompany() { return company; }

    public void setCompany(String company) { this.company = company; }

    public String getPosition() { return position; }

    public void setPosition(String position) { this.position = position; }

    public LocalDate getDateStarted() { return dateStarted; }

    public void setDateStarted(LocalDate dateStarted) { this.dateStarted = dateStarted; }

    public LocalDate getDateEnded() { return dateEnded; }

    public void setDateEnded(LocalDate dateEnded) { this.dateEnded = dateEnded; }

    public Boolean getIsFeatured() { return isFeatured; }

    public void setIsFeatured(Boolean isFeatured) { this.isFeatured = isFeatured; }

    public String getLanguage() { return language; }

    public void setLanguage(String language) { this.language = language; }
}