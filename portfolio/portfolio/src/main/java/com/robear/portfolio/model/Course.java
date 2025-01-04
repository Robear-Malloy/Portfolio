package com.robear.portfolio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "course_id")
    private Long id;

    @Basic(optional = false)
    @Column(name = "education_id")
    private Long educationId;

    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Long getEducationId() { return educationId; }

    public void setEducationId(Long educationId) { this.educationId = educationId; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
}
