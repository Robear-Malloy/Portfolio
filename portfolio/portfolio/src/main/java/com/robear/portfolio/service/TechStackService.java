package com.robear.portfolio.service;

import com.robear.portfolio.exception.InvalidTechStackException;
import com.robear.portfolio.exception.TechStackNotFoundException;
import com.robear.portfolio.model.TechStack;
import com.robear.portfolio.enums.TechType;
import com.robear.portfolio.repository.TechStackRepository;
import com.robear.portfolio.service.interfaces.ITechStackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechStackService implements ITechStackService {

    private static final Logger logger = LoggerFactory.getLogger(TechStackService.class);
    private final TechStackRepository techStackRepository;

    @Autowired
    public TechStackService(TechStackRepository techStackRepository) { this.techStackRepository = techStackRepository; }

    @Override
    public TechStack createTechStack(TechStack stack) {
        try {
            logger.info("Creating a new tech stack: {}", stack);
            if (stack.getProjectId() != null && stack.getExperienceId() != null) {
                logger.warn("Attempting to Add a Tech Stack with both Experience and Project");
                throw new InvalidTechStackException("Cannot Add Project/Experience");
            }
            return techStackRepository.save(stack);
        } catch (InvalidTechStackException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Error Creating new tech stack: {}, Error: {}",
                    stack, e.getMessage());
            throw new RuntimeException("Error has occurred creating Tech Stack");
        }
    }

    @Override
    public List<TechStack> getTechStack(Long id, TechType type) {
        try {
            logger.info("Getting Tech Stack for {} ID: {} ", type, id);
            List<TechStack> result;

            if (type == TechType.EXPERIENCE) {
                result = techStackRepository.findExperienceTechStack(id);
            } else {
                result = techStackRepository.findProjectTechStack(id);
            }

            if (result.isEmpty()) {
                throw new TechStackNotFoundException("No Tech Stack found with ID: " + id);
            }

            return result;
        } catch (TechStackNotFoundException e) {
            logger.warn("No Tech Stack Found with ID: {}", id);
            throw new TechStackNotFoundException("No Tech Stack found with ID:" + id);
        } catch (Exception e) {
            logger.error("Unexpected error occurred while finding tech stack id: {}", id);
            throw new RuntimeException("Error occurred finding Tech Stack");
        }
    }

    @Override
    public void deleteTechStack(Long id) {
        techStackRepository.deleteById(id);
        logger.info("Deleted Tech Stack ID: {}", id);
    }
}
