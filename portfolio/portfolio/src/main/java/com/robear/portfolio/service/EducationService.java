package com.robear.portfolio.service;

import com.robear.portfolio.model.Education;
import com.robear.portfolio.repository.EducationRepository;
import com.robear.portfolio.service.interfaces.IEducationService;
import com.robear.portfolio.exception.EducationNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationService implements IEducationService {

    private static Logger logger = LoggerFactory.getLogger(EducationService.class);
    private final EducationRepository educationRepository;

    @Autowired
    public EducationService(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    @Override
    public Education addEducation(Education education) {
        try {
            logger.info("Saving Education: {} to the Database",
                    education);
            return educationRepository.save(education);
        } catch (Exception e) {
            logger.error("Error Adding Education Object: {}.",
                    education);
            throw new RuntimeException("Unable to save education");
        }
    }

    @Cacheable(value="education")
    @Override
    public List<Education> getAllEducation() {
        try {
            logger.info("Retrieving All Education Results from Database");
            List<Education> educationList = educationRepository.findAll();

            if (educationList.isEmpty()) {
                throw new EducationNotFoundException("No Education Records Returned From Database");
            }

            return educationList;
        } catch (EducationNotFoundException e) {
            logger.warn("0 Education Results Returned");
            throw e;
        } catch (Exception e) {
            logger.error("Error Retrieving Education Results");
            throw new RuntimeException("Error Retrieving Education Information");
        }
    }

    @Override
    public Education getEducationById(Long id) {
        try {
            logger.info("Retrieving Education ID: {}",
                    id);
            return educationRepository.findById(id)
                    .orElseThrow(() -> {
                return new EducationNotFoundException(id);
            });
        } catch(EducationNotFoundException e) {
            logger.warn("No Education Found With ID: {}",
                    id);
            throw e;
        } catch (Exception e) {
            logger.error("Error Retrieving Education ID: {}",
                    id);
            throw new RuntimeException("Error Retrieving Education Information");
        }
    }

    @Override
    public Education updateEducationById(Long id, Education education) {
        try {
            logger.info("Updating Education ID: {} With Education Information: {}",
                    id, education);
            Education result = getEducationById(id);

            if (education.getSchool() != null && !education.getSchool().isEmpty()) {
                result.setSchool(education.getSchool());
            }
            if (education.getDegree() != null && !education.getDegree().isEmpty()) {
                result.setDegree(education.getDegree());
            }
            if (education.getGpa() != null && education.getGpa() != 0) {
                result.setGpa(education.getGpa());
            }
            if (education.getDateStarted() != null && !education.getDateStarted().isEmpty()) {
                result.setDateStarted(education.getDateStarted());
            }
            if (education.getDateEnded() != null && !education.getDateEnded().isEmpty()) {
                result.setDateEnded(education.getDateEnded());
            }

            return educationRepository.save(result);
        } catch (EducationNotFoundException e) {
            logger.warn("No Education to Update Found With Id: {}",
                    id);
            throw e;
        } catch (Exception e) {
            logger.error("Error Occurred While Updating Education ID: {}",
                    id);
            throw new RuntimeException("Error Updating Education Information");
        }
    }

    @Override
    public Education updateGpaById(Long id, Float gpa) {
        try {
            logger.info("Updating GPA: {} for Education ID: {}",
                    gpa, id);
            Education result = getEducationById(id);
            result.setGpa(gpa);
            return educationRepository.save(result);
        } catch (EducationNotFoundException e) {
            logger.warn("No Project Found to Update GPA for Education ID: {}",
                    id);
            throw e;
        } catch (Exception e) {
            logger.error("Error Updating GPA for Education ID: {}",
                    id);
            throw new RuntimeException("Error Updating Education GPA Information");
        }
    }

    @Override
    public void deleteEducation(Long id) {
        Education education = getEducationById(id);
        educationRepository.deleteById(id);
        logger.info("Deleted Project ID: {}",
                id);
    }
}
