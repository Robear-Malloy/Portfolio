package com.robear.portfolio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import java.time.LocalDate;

@Entity
public class Certification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "certification_id")
    private Long id;

    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    @Basic(optional = false)
    @Column(name = "date_completed")
    private LocalDate dateCompleted;

    @Basic(optional = false)
    @Column(name = "language")
    private String language;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public LocalDate getDateCompleted() { return dateCompleted; }

    public void setDateCompleted(LocalDate dateCompleted) { this.dateCompleted = dateCompleted; }

    public String getLanguage() { return language; }

    public void setLanguage(String language) { this.language = language; }
}
