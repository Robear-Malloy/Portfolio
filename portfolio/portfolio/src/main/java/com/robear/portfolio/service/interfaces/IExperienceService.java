package com.robear.portfolio.service.interfaces;

import com.robear.portfolio.model.Experience;

import java.util.List;

public interface IExperienceService {
    Experience addExperience(Experience experience);
    List<Experience> getAllExperiences(String lang);
    Experience getExperienceById(Long id);
    List<Experience> getAllFeaturedExperiences(String lang);
    Experience updateExperience(Long id, Experience experience);
    void deleteExperience(Long id);
}