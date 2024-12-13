package com.robear.portfolio.controller;

import com.robear.portfolio.controller.interfaces.IExperienceDescriptionController;
import com.robear.portfolio.model.ExperienceDescription;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/experience-description")
public class ExperienceDescriptionController implements IExperienceDescriptionController {

    @Override
    @PostMapping
    public ResponseEntity<ExperienceDescription> createExperienceDescription(ExperienceDescription description) {
        return null;
    }

    @Override
    @GetMapping
    public List<ExperienceDescription> getAllExperienceDescriptionById(Long id) {
        return List.of();
    }

    @Override
    @PutMapping
    public ResponseEntity<ExperienceDescription> updateExperienceDescription(Long id, String description) {
        return null;
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Void> deleteExperienceDescription(ExperienceDescription description) {
        return null;
    }
}
