package com.robear.portfolio.model;

import jakarta.persistence.*;

@Entity
public class ExperienceDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "experience_id")
    private Long id;

    @Column(name = "description")
    private String description;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }
}
