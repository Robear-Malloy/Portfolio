package com.robear.portfolio.controller.interfaces;

import com.robear.portfolio.model.Experience;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface IExperienceController {
    ResponseEntity<Experience> createExperience(Experience experience);
    ResponseEntity<List<Experience>> getAllExperiences();
    ResponseEntity<Experience> getExperienceById(Long id);
    ResponseEntity<List<Experience>> getFeaturedExperiences();
    ResponseEntity<Experience> updateExperienceById(Long id, Experience experience);
    ResponseEntity<Void> deleteExperienceById(Long id);
}