package com.robear.portfolio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;

import java.sql.Date;

@Entity
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "education_id")
    private Long id;

    @Basic(optional = false)
    @Column(name = "school")
    private String school;

    @Basic(optional = false)
    @Column(name = "degree")
    private String degree;

    @Column(name = "gpa")
    private Float gpa;

    @Basic(optional = false)
    @Column(name = "date_started")
    private String dateStarted;

    @Column(name = "date_ended")
    private String dateEnded;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSchool() { return school; }

    public void setSchool(String school) { this.school = school; }

    public String getDegree() { return degree; }

    public void setDegree(String degree) { this.degree = degree; }

    public Float getGpa() { return gpa; }

    public void setGpa(Float gpa) { this.gpa = gpa; }

    public String getDateStarted() { return dateStarted; }

    public void setDateStarted(String dateStarted) { this.dateStarted = dateStarted; }

    public String getDateEnded() { return dateEnded; }

    public void setDateEnded(String dateEnded) { this.dateEnded = dateEnded; }
}
