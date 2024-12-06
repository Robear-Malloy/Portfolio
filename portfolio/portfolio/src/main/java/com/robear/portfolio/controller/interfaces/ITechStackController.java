package com.robear.portfolio.controller.interfaces;

import com.robear.portfolio.model.TechStack;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface ITechStackController {
    ResponseEntity<TechStack> addTechStack(TechStack techStack);
    ResponseEntity<List<TechStack>> getProjectTechStack(Long id);
    ResponseEntity<List<TechStack>> getExperienceTechStack(Long id);
    ResponseEntity<Void> deleteTechStack(Long id);
}
