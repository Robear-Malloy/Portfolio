package com.robear.portfolio.controller.interfaces;

import com.robear.portfolio.model.ExperienceDescription;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IExperienceDescriptionController {
    ResponseEntity<ExperienceDescription> createExperienceDescription(ExperienceDescription description);
    List<ExperienceDescription> getAllExperienceDescriptionById(Long id);
    ResponseEntity<ExperienceDescription> updateExperienceDescription(Long id, String description);
    ResponseEntity<Void> deleteExperienceDescription(ExperienceDescription description);
}
