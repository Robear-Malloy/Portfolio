package com.robear.portfolio.service;

import com.robear.portfolio.model.Education;
import com.robear.portfolio.repository.EducationRepository;
import com.robear.portfolio.service.interfaces.IEducationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
        return educationRepository.save(education);
    }

    @Override
    public List<Education> getAllEducation() {
        return educationRepository.findAll();
    }

    @Override
    public Education getEducationById(Long id) {
        return educationRepository.findById(id).orElseThrow();
    }

    @Override
    public Education updateEducationById(Long id, Education education) {
        return educationRepository.save(education);
    }

    @Override
    public Education updateGpaById(Long id, Float gpa) {
        return educationRepository.save(new Education());
    }

    @Override
    public void deleteEducation(Long id) {
        educationRepository.deleteById(id);
    }
}
