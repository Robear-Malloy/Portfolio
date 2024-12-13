package com.robear.portfolio.service.interfaces;

import com.robear.portfolio.model.ExperienceDescription;

import java.util.List;

public interface IExperienceDescriptionService {
    ExperienceDescription addExperienceDescription();
    List<ExperienceDescription> getExperienceDescriptions();
    ExperienceDescription updateExperienceDescription(ExperienceDescription description);
    void deleteExperienceDescription();
}
