package com.robear.portfolio.service.interfaces;

import com.robear.portfolio.model.Experience;

import java.util.List;

public interface IExperienceService {
    Experience addExperience(Experience experience);
    List<Experience> getAllExperiences();
    Experience getExperienceById(Long id);
    Experience updateExperience(Long id, Experience experience);
    void deleteExperience(Long id);
}