package com.robear.portfolio.service;

import com.robear.portfolio.exception.ExperienceNotFoundException;
import com.robear.portfolio.model.Experience;
import com.robear.portfolio.repository.ExperienceRepository;
import com.robear.portfolio.service.interfaces.IExperienceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
        try {
            logger.info("Adding Experience to Database: {}", experience);
            return experienceRepository.save(experience);
        } catch (Exception e) {
            logger.error("Error While Adding to Database Experience: {}", experience);
            throw new RuntimeException("Error Adding Experinece");
        }
    }

    @Cacheable(value="experiences")
    @Override
    public List<Experience> getAllExperiences(String lang) {
        try {
            logger.info("Retrieving All Experiences from Database");
            List<Experience> experiences = experienceRepository.findAllByLanguage(lang);
            if (experiences.isEmpty()) {
                throw new ExperienceNotFoundException("No Experiences Found in Database");
            }
            return experiences;
        } catch (ExperienceNotFoundException e) {
            logger.warn("No Experiences Found while Retrieving All");
            throw e;
        } catch (Exception e) {
            logger.error("Error Occurred Retrieving All Experiences from Database");
            throw new RuntimeException("Error Retrieving Experiences");
        }
    }

    @Override
    public Experience getExperienceById(Long id) {
        try {
            logger.info("Retrieving Experience ID: {}",
                    id);
            return experienceRepository.findById(id)
                    .orElseThrow(() -> {
                return new ExperienceNotFoundException(id);
            });
        } catch (ExperienceNotFoundException e) {
            logger.warn("No Experience ID: {} Found in DB",
                    id);
            throw e;
        } catch (Exception e) {
            logger.error("Error Retrieving Experience ID: {} from DB",
                    id);
            throw new RuntimeException("Error Retrieving Experience ID: " + id);
        }
    }

    @Cacheable(value="experiences")
    @Override
    public List<Experience> getAllFeaturedExperiences(String lang) {
        try {
            logger.info("Retrieving All Featured Experiences from Database");
            List<Experience> experiences = experienceRepository.findFeatured(lang);
            if (experiences.isEmpty()) {
                throw new ExperienceNotFoundException("No Experiences Found in Database");
            }
            return experiences;
        } catch (ExperienceNotFoundException e) {
            logger.warn("No Featured Experiences Found while Retrieving All");
            throw e;
        } catch (Exception e) {
            logger.error("Error Occurred Retrieving All Featured Experiences from Database");
            throw new RuntimeException("Error Retrieving Experiences");
        }
    }

    @Override
    public Experience updateExperience(Long id, Experience experience) {
        try {
            logger.info("Updating Experience ID: {} With experience: {}",
                    id, experience);
            Experience result = getExperienceById(id);

            if (experience.getCompany() != null && !experience.getCompany().isEmpty()) {
                result.setCompany(experience.getCompany());
            }
            if (experience.getPosition() != null && !experience.getPosition().isEmpty()) {
                result.setPosition(experience.getPosition());
            }
            if (experience.getDateStarted() != null) {
                result.setDateStarted(experience.getDateStarted());
            }
            if (experience.getDateEnded() != null) {
                result.setDateEnded(experience.getDateEnded());
            }

            return experienceRepository.save(result);
        } catch (ExperienceNotFoundException e) {
            logger.warn("No Experience Found to Update For ID: {}",
                    id);
            throw e;
        } catch (Exception e) {
            logger.error("Error Occurred Updating Experience ID: {}",
                    id);
            throw new RuntimeException("Error Updating Experience");
        }
    }

    @Override
    public void deleteExperience(Long id) {
        try {
            Experience experience = getExperienceById(id);
            experienceRepository.deleteById(id);
            logger.info("Deleted Experience ID: {}", id);
        } catch (ExperienceNotFoundException e) {
            logger.warn("No Experience Found to Delete. ID: {}",
                    id);
            throw e;
        } catch (Exception e) {
            logger.error("Error Occurred Deleting Experience ID: {} from Database",
                    id);
            throw new RuntimeException("Error Deleting Experience");
        }
    }
}