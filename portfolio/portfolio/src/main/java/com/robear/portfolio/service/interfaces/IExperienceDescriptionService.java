package com.robear.portfolio.service.interfaces;

import com.robear.portfolio.model.ExperienceDescription;

import java.util.List;

public interface IExperienceDescriptionService {
    ExperienceDescription addExperienceDescription(ExperienceDescription description);
    List<ExperienceDescription> getExperienceDescriptions(Long id);
    ExperienceDescription updateExperienceDescription(ExperienceDescription description);
    void deleteExperienceDescription(ExperienceDescription description);
}
