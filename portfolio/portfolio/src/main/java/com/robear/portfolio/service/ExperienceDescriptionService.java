package com.robear.portfolio.service;

import com.robear.portfolio.model.ExperienceDescription;
import com.robear.portfolio.service.interfaces.IExperienceDescriptionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperienceDescriptionService implements IExperienceDescriptionService {
    @Override
    public ExperienceDescription addExperienceDescription() {
        return null;
    }

    @Override
    public List<ExperienceDescription> getExperienceDescriptions() {
        return List.of();
    }

    @Override
    public ExperienceDescription updateExperienceDescription(ExperienceDescription description) {
        return null;
    }

    @Override
    public void deleteExperienceDescription() {

    }
}
