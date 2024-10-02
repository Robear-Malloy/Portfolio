package com.robear.portfolio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "project_id")
    private Long id;

    @Basic(optional = false)
    @Column(name = "title")
    private String title;

    @Basic(optional = false)
    @Column(name = "description")
    private String description;

    @Column(name = "repo")
    private String repoLink;

    @Column(name = "demo")
    private String demoLink;

    @Column(name = "photo")
    private String photoFile;

    @Basic(optional = false)
    @Column(name = "is_featured")
    private Boolean isFeatured;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public Boolean getIsFeatured() { return isFeatured; }

    public void setIsFeatured(Boolean isFeatured) { this.isFeatured = isFeatured; }

    public String getRepoLink() { return repoLink; }

    public void setRepoLink(String repoLink) { this.repoLink = repoLink; }

    public String getDemoLink() { return demoLink; }

    public void setDemoLink(String demoLink) { this.demoLink = demoLink; }

    public String getPhotoFile() { return photoFile; }

    public void setPhotoFile(String photoFile) { this.photoFile = photoFile; }
}
