package com.robear.portfolio.service;

import com.robear.portfolio.exception.ExperienceDescriptionNotFoundException;
import com.robear.portfolio.model.ExperienceDescription;
import com.robear.portfolio.repository.ExperienceDescriptionRepository;
import com.robear.portfolio.service.interfaces.IExperienceDescriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExperienceDescriptionService implements IExperienceDescriptionService {
    private final static Logger logger = LoggerFactory.getLogger(ExperienceService.class);
    private final ExperienceDescriptionRepository experienceDescriptionRepository;

    @Autowired
    public ExperienceDescriptionService(ExperienceDescriptionRepository experienceDescriptionRepository) {
        this.experienceDescriptionRepository = experienceDescriptionRepository;
    }

    @Override
    public ExperienceDescription addExperienceDescription(ExperienceDescription description) {
        try {
            logger.info("Adding Experience Description: {}", description);
            return experienceDescriptionRepository.save(description);
        } catch (Exception e) {
            logger.error("Error Adding Experience Description to Database.", e);
            throw new RuntimeException("Failed to add experience description.", e);
        }
    }

    @Cacheable(value="experienceDescriptions")
    @Override
    public List<ExperienceDescription> getExperienceDescriptions(Long experienceId) {
        try {
            logger.info("Getting Experience Descriptions for id: {}", experienceId);
            List<ExperienceDescription> descriptions =
                    experienceDescriptionRepository.findAllDescriptionByExperienceId(experienceId);
            if (descriptions.isEmpty()) {
                throw new ExperienceDescriptionNotFoundException("No descriptions found for experience ID: " + experienceId);
            }
            return descriptions;
        } catch (ExperienceDescriptionNotFoundException e) {
            logger.warn("Unable to find experience descriptions in database for id: {}", experienceId);
            throw new ExperienceDescriptionNotFoundException("No description found");
        } catch (Exception e) {
            logger.error("Error occurred getting experience descriptions from database.", e);
            throw new RuntimeException("Failed to retrieve experience descriptions.", e);
        }
    }

    @Override
    public ExperienceDescription updateExperienceDescription(ExperienceDescription description) {
        try {
            logger.info("Updating Experience Description: {}", description);
            Optional<ExperienceDescription> existingDescription = experienceDescriptionRepository.findById(description.getId());
            if (existingDescription.isEmpty()) {
                throw new ExperienceDescriptionNotFoundException("No description found for ID: " + description.getId());
            }
            return experienceDescriptionRepository.save(description);
        } catch (ExperienceDescriptionNotFoundException e) {
            logger.warn("Unable to find experience description that matches the id: {}", description.getId());
            throw new ExperienceDescriptionNotFoundException("No description found");
        } catch (Exception e) {
            logger.error("Error Updating Experience Description", e);
            throw new RuntimeException("Failed to update experience description.", e);
        }
    }

    @Override
    public void deleteExperienceDescription(ExperienceDescription description) {
        try {
            logger.info("Deleting Experience Description: {}", description);
            Optional<ExperienceDescription> existingDescription = experienceDescriptionRepository.findById(description.getId());
            if (existingDescription.isEmpty()) {
                throw new ExperienceDescriptionNotFoundException("No description found for ID: " + description.getId());
            }
            experienceDescriptionRepository.deleteById(description.getId());
        } catch (ExperienceDescriptionNotFoundException e) {
            logger.warn("Unable to find experience description for id: {}", description.getId());
            throw new ExperienceDescriptionNotFoundException("No description found");
        } catch (Exception e) {
            logger.error("Error Deleting Experience Description", e);
            throw new RuntimeException("Failed to delete experience description.", e);
        }
    }
}
