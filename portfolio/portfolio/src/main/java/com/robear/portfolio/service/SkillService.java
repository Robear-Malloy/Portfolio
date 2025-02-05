package com.robear.portfolio.service;

import com.robear.portfolio.exception.SkillNotFoundException;
import com.robear.portfolio.service.interfaces.ISkillService;
import com.robear.portfolio.model.Skill;
import com.robear.portfolio.enums.SkillType;
import com.robear.portfolio.repository.SkillRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.stream.*;
import java.util.List;

@Service
public class SkillService implements ISkillService {

    private static Logger logger = LoggerFactory.getLogger(SkillService.class);
    private final SkillRepository skillRepository;

    @Autowired
    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public Skill addSkill(Skill skill) {
        try {
            return skillRepository.save(skill);
        } catch (Exception e) {
            logger.error("Error adding skill", e);
            throw new RuntimeException("Unable to add skill", e);
        }
    }

    @Cacheable(value="skills")
    @Override
    public List<Skill> getAllSkills(String lang) {
        try {
            return skillRepository.findByLanguage(lang);
        } catch(Exception e) {
            logger.error("Error retrieving skills", e);
            throw new RuntimeException("Unable to retrieve skills", e);
        }
    }

    @Override
    public Skill getSkillById(Long id) {
        return skillRepository.findById(id).
                orElseThrow(() -> {
                    logger.warn("Skill not found with ID: {}", id);
                    return new SkillNotFoundException(id);
                });
    }

    @Override
    public List<Skill> getSkillsOfType(SkillType type) {
        List<Skill> skills =  skillRepository.findByType(type);
        if (skills.isEmpty()) {
            logger.warn("No skills found of type: {}", type);
            throw new SkillNotFoundException(type);
        }
        return skills;

        /* Keeping this here for myself, this is how I could use Java Stream to handle it.
        return skillRepository.findAll()
                .stream()
                .filter(e -> e.getType().equals(type))
                .collect(Collectors.toList());
         */
    }

    @Override
    public void deleteSkill(Long id) {
        Skill skill = getSkillById(id);
        skillRepository.deleteById(id);
        logger.info("Deleted skill ID: {}", id);
    }
}