package com.robear.portfolio.unit.service;

import com.robear.portfolio.service.EducationService;
import com.robear.portfolio.exception.EducationNotFoundException;
import com.robear.portfolio.model.Education;
import com.robear.portfolio.repository.EducationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class EducationServiceTest {
    @InjectMocks
    EducationService educationService;

    private final Education education = new Education();

    @Mock
    EducationRepository educationRepository;

    @BeforeEach
    public void setup() {
        education.setId(1L);
        education.setSchool("Test University");
        education.setDegree("4.0");
        education.setGpa(4.0f);
        education.setDateStarted(LocalDate.of(2020, 1, 1));
        education.setDateEnded(LocalDate.of(2020, 1, 1));
        education.setLanguage("en");
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddEducationWhenSuccessful() {
        when(educationRepository.save(education))
                .thenReturn(education);

        Education result =
                educationService.addEducation(education);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(education);
        verify(educationRepository).save(education);
    }

    @Test
    public void testAddEducationWhenThrowsException() {
        when(educationRepository.save(education))
                .thenThrow(new RuntimeException(""));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            educationService.addEducation(education);
        });

        assertThat(exception.getMessage())
                .isEqualTo("Unable to save education");
        verify(educationRepository).save(education);
    }

    @Test
    public void testGetAllEducationWhenRecordsFound() {
        List<Education> educationList = Collections.singletonList(education);
        when(educationRepository.findAllByLanguage("en"))
                .thenReturn(educationList);

        List<Education> result =
                educationService.getAllEducation(education.getLanguage());

        assertThat(result).isNotNull();
        assertThat(result)
                .isEqualTo(educationList);
        verify(educationRepository).findAllByLanguage("en");
    }

    @Test
    public void testGetAllEducationWhenNoRecordsFound() {
        when(educationRepository.findAllByLanguage("en"))
                .thenReturn(Collections.emptyList());

        EducationNotFoundException exception = assertThrows(EducationNotFoundException.class, () -> {
            educationService.getAllEducation(education.getLanguage());
        });

        assertThat(exception.getMessage())
                .isEqualTo("No Education Records Returned From Database");
        verify(educationRepository).findAllByLanguage("en");
    }

    @Test
    public void testGetAllEducationWhenThrowsException() {
        when(educationRepository.findAllByLanguage("en"))
                .thenThrow(new RuntimeException(""));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            educationService.getAllEducation(education.getLanguage());
        });

        assertThat(exception.getMessage())
                .isEqualTo("Error Retrieving Education Information");
        verify(educationRepository).findAllByLanguage("en");
    }

    @Test
    public void testGetEducationByIdWhenEducationExists() {
        when(educationRepository.findById(education.getId()))
                .thenReturn(Optional.of(education));

        Education result =
                educationService.getEducationById(education.getId());

        assertThat(result).isNotNull();
        assertThat(result)
                .isEqualTo(education);
        verify(educationRepository).findById(education.getId());
    }

    @Test
    public void testGetEducationByIdWhenNoEducationReturned() {
        when(educationRepository.findById(education.getId()))
                .thenReturn(Optional.empty());

        EducationNotFoundException exception = assertThrows(EducationNotFoundException.class, () -> {
            educationService.getEducationById(education.getId());
        });

        assertThat(exception.getMessage())
                .isEqualTo("Education not found with ID: " + education.getId());
    }

    @Test
    public void testGetEducationByIdWhenThrowsException() {
        when(educationRepository.findById(education.getId()))
                .thenThrow(new RuntimeException(""));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
           educationService.getEducationById(education.getId());
        });

        assertThat(exception.getMessage())
                .isEqualTo("Error Retrieving Education Information");
        verify(educationRepository).findById(education.getId());
    }

    @Test
    public void testUpdateEducationWhenSuccess() {
        Education updatedEducation = new Education();
        updatedEducation.setSchool("New University");

        when(educationRepository.findById(education.getId()))
                .thenReturn(Optional.of(education));
        when(educationRepository.save(education))
                .thenReturn(education);

        Education result = educationService.updateEducationById(education.getId(), updatedEducation);

        assertThat(result).isNotNull();
        assertThat(result.getSchool())
                .isEqualTo(updatedEducation.getSchool());
        verify(educationRepository).save(education);
    }

    @Test
    public void testUpdateEducationWhenNoRecordsFound() {
        when(educationRepository.findById(education.getId()))
                .thenReturn(Optional.empty());

        EducationNotFoundException exception = assertThrows(EducationNotFoundException.class, () -> {
            educationService.updateEducationById(education.getId(), education);
        });

        assertThat(exception.getMessage())
                .isEqualTo("Education not found with ID: " + education.getId());
        verify(educationRepository).findById(education.getId());
    }

    @Test
    public void testUpdateEducationWhenThrowsException() {
        when(educationRepository.findById(education.getId()))
                .thenThrow(new RuntimeException());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            educationService.updateEducationById(education.getId(), education);
        });

        assertThat(exception.getMessage())
                .isEqualTo("Error Updating Education Information");
        verify(educationRepository).findById(education.getId());
    }

    @Test
    public void testUpdateEducationWhenAllFieldsUpdated() {
        Education updatedEducation = new Education();
        updatedEducation.setSchool("New University");
        updatedEducation.setDegree("New Degree");
        updatedEducation.setGpa(1.0f);
        updatedEducation.setDateStarted(LocalDate.of(2020, 1, 1));
        updatedEducation.setDateEnded(LocalDate.of(2020, 1, 1));

        when(educationRepository.findById(education.getId()))
                .thenReturn(Optional.of(education));
        when(educationRepository.save(education))
                .thenReturn(education);

        Education result = educationService.updateEducationById(education.getId(), updatedEducation);

        assertThat(result).isNotNull();
        assertThat(result.getSchool())
                .isEqualTo(updatedEducation.getSchool());
        assertThat(result.getGpa())
                .isEqualTo(updatedEducation.getGpa());
        assertThat(result.getDegree())
                .isEqualTo(updatedEducation.getDegree());
        assertThat(result.getDateStarted())
                .isEqualTo(updatedEducation.getDateStarted());
        assertThat(result.getDateEnded())
                .isEqualTo(updatedEducation.getDateEnded());
        verify(educationRepository).save(education);
    }

    @Test
    public void testUpdateGpaByIdWhenSuccessful() {
        float newGpa = 1.0f;
        when(educationRepository.findById(education.getId()))
                .thenReturn(Optional.of(education));
        when(educationRepository.save(education))
                .thenReturn(education);

        Education result = educationService.updateGpaById(education.getId(), newGpa);

        assertThat(result).isNotNull();
        assertThat(result.getGpa()).isEqualTo(newGpa);
        verify(educationRepository).save(education);
    }

    @Test
    public void testUpdateGpaByIdWhenEducationNotFound() {
        when(educationRepository.findById(education.getId()))
                .thenReturn(Optional.empty());

        EducationNotFoundException exception = assertThrows(EducationNotFoundException.class, () -> {
           educationService.updateGpaById(education.getId(), 1.0f);
        });

        assertThat(exception.getMessage())
                .isEqualTo("Education not found with ID: " + education.getId());
        verify(educationRepository).findById(education.getId());
    }

    @Test
    public void testUpdateGpaByIdWhenThrowsException() {
        when(educationRepository.findById(education.getId()))
                .thenThrow(new RuntimeException());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            educationService.updateGpaById(education.getId(), 1f);
        });

        assertThat(exception.getMessage())
                .isEqualTo("Error Updating Education GPA Information");
        verify(educationRepository).findById(education.getId());
    }

    @Test
    public void testDeleteProjectWhenSuccessful() {
        when(educationRepository.findById(education.getId()))
                .thenReturn(Optional.of(education));

        educationService.deleteEducation(education.getId());

        verify(educationRepository).deleteById(education.getId());
    }
}
