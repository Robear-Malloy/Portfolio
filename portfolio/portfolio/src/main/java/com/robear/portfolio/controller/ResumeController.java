package com.robear.portfolio.controller;

import com.robear.portfolio.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resume")
public class ResumeController {
    private static final Logger logger = LoggerFactory.getLogger(ResumeController.class);

    @Autowired
    private EducationController educationController;

    @Autowired
    private ExperienceController experienceController;

    @Autowired
    private ProjectController projectController;

    @Autowired
    private SkillController skillController;

    @GetMapping
    public ResponseEntity<Object> getResume() {
        try {
            ResponseEntity<List<Education>> educationResponse = educationController.getAllEducation();
            ResponseEntity<List<Experience>> experienceResponse = experienceController.getAllExperiences();
            ResponseEntity<List<Project>> projectResponse = projectController.getAllProjects();
            ResponseEntity<List<Skill>> skillResponse = skillController.getAllSkills();

            Resume resumeData = new Resume();
            resumeData.setEducation(educationResponse.getBody());
            resumeData.setExperience(experienceResponse.getBody());
            resumeData.setProjects(projectResponse.getBody());
            resumeData.setSkills(skillResponse.getBody());

            return new ResponseEntity<>(resumeData, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching resume data", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
