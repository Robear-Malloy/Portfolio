package com.robear.portfolio.model;

import com.robear.portfolio.enums.SkillType;

import jakarta.persistence.*;

@Entity
public class Skill
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "skill_id")
    private Long id;

    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.ORDINAL)
    @Basic(optional = false)
    @Column(name = "type")
    private SkillType type;

    @Basic(optional = false)
    @Column(name = "language")
    private String language;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SkillType getType() { return type; }

    public void setType(SkillType type) {this.type = type;}

    public String getLanguage() { return language; }

    public void setLanguage(String language) { this.language = language; }
}