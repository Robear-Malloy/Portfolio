package com.robear.portfolio.service;

import com.robear.portfolio.model.Skill;
import com.robear.portfolio.enums.SkillType;
import com.robear.portfolio.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.*;
import java.util.List;

@Service
public class SkillService {

    private final SkillRepository skillRepository;

    @Autowired
    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public Skill addSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    public Skill getSkillById(Long id) {
        return skillRepository.findById(id).orElse(null);
    }

    public List<Skill> getSkillsOfType(SkillType type) {
        return skillRepository.findAll().stream().filter(e -> e.getType().equals(type)).collect(Collectors.toList());
    }

    public void deleteSkill(Long id) {
        skillRepository.deleteById(id);
    }
}