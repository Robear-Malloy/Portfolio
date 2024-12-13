package com.robear.portfolio.service;

import com.robear.portfolio.model.ExperienceDescription;
import com.robear.portfolio.repository.ExperienceDescriptionRepository;
import com.robear.portfolio.service.interfaces.IExperienceDescriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperienceDescriptionService implements IExperienceDescriptionService {
    private static Logger logger = LoggerFactory.getLogger(ExperienceService.class);
    private final ExperienceDescriptionRepository experienceDescriptionRepository;

    @Autowired
    public ExperienceDescriptionService(ExperienceDescriptionRepository experienceDescriptionRepository)
    { this.experienceDescriptionRepository = experienceDescriptionRepository; }

    @Override
    public ExperienceDescription addExperienceDescription(ExperienceDescription description) {
        return null;
    }

    @Override
    public List<ExperienceDescription> getExperienceDescriptions(Long id) {
        return List.of();
    }

    @Override
    public ExperienceDescription updateExperienceDescription(ExperienceDescription description) {
        return null;
    }

    @Override
    public void deleteExperienceDescription(ExperienceDescription description) {

    }
}
