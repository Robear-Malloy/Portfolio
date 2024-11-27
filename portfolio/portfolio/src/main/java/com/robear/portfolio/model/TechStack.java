package com.robear.portfolio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;

@Entity
public class TechStack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tech_stack_id")
    private Long id;

    @Basic(optional = false)
    @Column(name = "skill_id")
    private Long skillId;

    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "experience_id")
    private Long experienceId;

    public Long getId() { return this.id; }
    public void setId(Long id) { this.id = id; }
    public Long getSkillId() { return this.skillId; }
    public void setSkillId(Long skillId) { this.skillId = skillId; }
    public Long getProjectId() { return this.projectId; }
    public void setProjectId(Long projectId) { this.projectId = projectId; }
    public Long getExperienceId() {return this.experienceId; }
    public void setExperienceId(Long experienceId) {this.experienceId = experienceId; }
}
