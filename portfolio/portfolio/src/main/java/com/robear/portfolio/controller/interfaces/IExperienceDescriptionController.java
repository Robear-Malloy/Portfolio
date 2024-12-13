package com.robear.portfolio.controller.interfaces;

import com.robear.portfolio.model.ExperienceDescription;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IExperienceDescriptionController {
    ResponseEntity<ExperienceDescription> createExperienceDescription(ExperienceDescription description);
    ResponseEntity<List<ExperienceDescription>> getAllExperienceDescriptionById(Long id);
    ResponseEntity<ExperienceDescription> updateExperienceDescription(ExperienceDescription description);
    ResponseEntity<Void> deleteExperienceDescription(ExperienceDescription description);
}
