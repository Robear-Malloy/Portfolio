package com.robear.portfolio.service

import com.robear.portfolio.exception.ExperienceNotFoundException;
import com.robear.portfolio.model.Experience;
import com.robear.portfolio.repository.ExperienceRepository;
import com.robear.portfolio.service.interfaces.IExperienceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperienceService implements IExperienceService {
    private static Logger logger = LoggerFactory.getLogger(ExperienceService.class);
    private final ExperienceRepository experienceRepository;

    @Autowired
    public ExperienceService(ExperienceRepository experienceRepository) { this.experienceRepository = experienceRepository; }

    @Override
    public Experience addExperience(Experience experience) {

    }

    @Override
    public List<Experience> getAllExperiences() {

    }

    @Override
    public Experience getExperienceById(Long id) {

    }

    @Override
    public Experience updateExperience(Long id, Experience experience) {

    }

    @Override
    public void deleteExperience(Long id) {

    }
}