package com.robear.portfolio.service;

import com.robear.portfolio.service.interfaces.ISkillService;
import com.robear.portfolio.model.Skill;
import com.robear.portfolio.enums.SkillType;
import com.robear.portfolio.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.*;
import java.util.List;

@Service
public class SkillService implements ISkillService {

    private final SkillRepository skillRepository;

    @Autowired
    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public Skill addSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    @Override
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    @Override
    public Skill getSkillById(Long id) {
        return skillRepository.findById(id).orElse(null);
    }

    @Override
    public List<Skill> getSkillsOfType(SkillType type) {
        return skillRepository.findByType(type);

        /* Keeping this here for myself, this is how I could use Java Stream to handle it.
        return skillRepository.findAll()
                .stream()
                .filter(e -> e.getType().equals(type))
                .collect(Collectors.toList());
         */
    }

    @Override
    public void deleteSkill(Long id) {
        skillRepository.deleteById(id);
    }
}