package com.robear.portfolio.controller.interfaces;

import com.robear.portfolio.model.Education;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface IEducationController {
    ResponseEntity<Education> createEducation(Education education);
    ResponseEntity<List<Education>> getAllEducation();
    ResponseEntity<Education> getEducationById(Long id);
    ResponseEntity<Education> updateEducationById(Long id, Education education);
    ResponseEntity<Education> updateGpaById(Long id, Float gpa);
    ResponseEntity<Void> deleteEducation(Long id);
}
