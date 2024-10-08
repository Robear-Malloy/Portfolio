package com.robear.portfolio.service.interfaces;

import com.robear.portfolio.model.Education;
import java.util.List;

public interface IEducationService {
    Education addEducation(Education education);
    List<Education> getAllEducation();
    Education getEducationById(Long id);
    Education updateEducationById(Long id, Education education);
    Education updateGpaById(Long id, Float gpa);
    void deleteEducation(Long id);
}
