package com.robear.portfolio.controller;

import com.robear.portfolio.controller.interfaces.ISkillController;
import com.robear.portfolio.exception.SkillNotFoundException;
import com.robear.portfolio.model.Skill;
import com.robear.portfolio.enums.SkillType;
import com.robear.portfolio.service.SkillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillController implements ISkillController {

    private static final Logger logger = LoggerFactory.getLogger(SkillController.class);

    @Autowired
    private SkillService skillService;

    @PostMapping
    @Override
    public ResponseEntity<Skill> createSkill(@RequestBody Skill skill) {
        try {
            logger.info("Adding Skill: {}", skill);
            Skill newSkill = skillService.addSkill(skill);
            return new ResponseEntity<>(newSkill, HttpStatus.CREATED);
        } catch(Exception e) {
            logger.error("Error Creating Skill: {}, Exception Message: {}",
                    skill, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    @Override
    public ResponseEntity<List<Skill>> getAllSkills(
            @RequestParam(value = "lang", required = false, defaultValue = "en") String lang
    ) {
        try {
            logger.info("Getting All Skills");
            List<Skill> skills = skillService.getAllSkills(lang);
            return new ResponseEntity<>(skills, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error Getting Skills. Exception Message: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/type/{type}")
    @Override
    public ResponseEntity<List<Skill>> getSkillsByType(
            @PathVariable("type") int type) {
        try {
            SkillType skillType = SkillType.fromValue(type);
            List<Skill> skills = skillService.getSkillsOfType(skillType);
            return new ResponseEntity<>(skills, HttpStatus.OK);
        } catch (SkillNotFoundException e) {
            logger.warn("Attempted to Retrieve Type {}, but not found: {}",
                    type, e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error deleting skill with Type {}: {}",
                    type, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/id/{id}")
    @Override
    public  ResponseEntity<Skill> getSkillById(
            @PathVariable("id") Long id) {
        try {
            Skill skill = skillService.getSkillById(id);
            return new ResponseEntity<>(skill, HttpStatus.OK);
        } catch (SkillNotFoundException e) {
            logger.warn("Attempted to Retrieve ID: {}, but not found: {}",
                    id, e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error Retrieving skill with ID {}: {}",
                    id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/id/{id}")
    @Override
    public ResponseEntity<Void> deleteSkillById(
            @PathVariable("id") Long id)
    {
        try {
            skillService.deleteSkill(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch(SkillNotFoundException e) {
            logger.warn("Attempted to Delete ID {}, but not found: {}",
                    id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch(Exception e) {
            logger.error("Error deleting skill with ID {}: {}",
                    id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}