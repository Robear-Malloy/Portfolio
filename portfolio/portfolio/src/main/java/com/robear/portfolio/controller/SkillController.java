package com.robear.portfolio.controller;

import com.robear.portfolio.model.Skill;
import com.robear.portfolio.enums.SkillType;
import com.robear.portfolio.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @PostMapping
    public ResponseEntity<Skill> createSkill(@RequestBody Skill skill) {
        Skill newSkill = skillService.addSkill(skill);
        return new ResponseEntity<>(newSkill, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Skill>> getAllSkills() {
        List<Skill> skills = skillService.getAllSkills();
        return new ResponseEntity<>(skills, HttpStatus.OK);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Skill>> getSkillsByType(
            @PathVariable("type") int type) {
        SkillType skillType = SkillType.fromValue(type);
        List<Skill> skills = skillService.getSkillsOfType(skillType);
        return new ResponseEntity<>(skills, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public  ResponseEntity<Skill> getSkillById(
            @PathVariable("id") Long id) {
        Skill skill = skillService.getSkillById(id);
        return new ResponseEntity<>(skill, HttpStatus.OK);
    }

    @DeleteMapping("/id/{id}")
    public void deleteSkillById(
            @PathVariable("id") Long id)
    {
        skillService.deleteSkill(id);
    }
}