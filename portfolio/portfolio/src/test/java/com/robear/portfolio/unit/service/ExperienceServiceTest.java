package com.robear.portfolio.unit.service;

import com.robear.portfolio.repository.ExperienceRepository;
import com.robear.portfolio.service.ExperienceService;
import com.robear.portfolio.exception.ExperienceNotFoundException;
import com.robear.portfolio.model.Experience;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ExperienceServiceTest {
    private final Experience experience = new Experience();

    @InjectMocks
    ExperienceService experienceService;

    @Mock
    ExperienceRepository experienceRepository;

    @BeforeEach
    public void setup() {
        experience.setId(1L);
        experience.setCompany("Test Company");
        experience.setPosition("Tester");
        experience.setDateStarted(LocalDate.now().toString());
        experience.setDateEnded(LocalDate.now().toString());
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddExperienceWhenSuccessful() {
        when(experienceRepository.save(experience))
                .thenReturn(experience);

        Experience result =
                experienceService.addExperience(experience);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(experience);
        verify(experienceRepository).save(experience);
    }

    @Test
    public void testAddExperienceWhenThrowsException() {
        when(experienceRepository.save(experience))
                .thenThrow(new RuntimeException());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            experienceService.addExperience(experience);
        });

        assertThat(exception.getMessage())
                .isEqualTo("Error Adding Experinece");
        verify(experienceRepository).save(experience);
    }

    @Test
    public void testGetAllExperiencesWhenRecordsFound() {
        List<Experience> experiences = Collections.singletonList(experience);
        when(experienceRepository.findAll())
                .thenReturn(experiences);

        List<Experience> result =
                experienceService.getAllExperiences();

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(experiences);
        verify(experienceRepository).findAll();
    }

    @Test
    public void testGetAllExperiencesWhenNoRecordsFound() {
        when(experienceRepository.findAll())
                .thenReturn(Collections.emptyList());

        ExperienceNotFoundException exception = assertThrows(ExperienceNotFoundException.class, () -> {
           experienceService.getAllExperiences();
        });

        assertThat(exception.getMessage())
                .isEqualTo("No Experiences Found in Database");
        verify(experienceRepository).findAll();
    }

    @Test
    public void testGetAllExperiencesWhenThrowsException() {
        when(experienceRepository.findAll())
                .thenThrow(new RuntimeException(""));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            experienceService.getAllExperiences();
        });

        assertThat(exception.getMessage())
                .isEqualTo("Error Retrieving Experiences");
        verify(experienceRepository).findAll();
    }

    @Test
    public void testGetExperienceByIdWhenExperienceExists() {
        when(experienceRepository.findById(experience.getId()))
                .thenReturn(Optional.of(experience));

        Experience result =
                experienceService.getExperienceById(experience.getId());

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(experience);
        verify(experienceRepository).findById(experience.getId());
    }

    @Test
    public void testGetExperienceByIdWhenNoExperienceReturned() {
        when(experienceRepository.findById(experience.getId()))
                .thenReturn(Optional.empty());

        ExperienceNotFoundException exception = assertThrows(ExperienceNotFoundException.class, () -> {
            experienceService.getExperienceById(experience.getId());
        });

        assertThat(exception.getMessage())
                .isEqualTo("Experience not found with ID: " + experience.getId());
        verify(experienceRepository).findById(experience.getId());
    }

    @Test
    public void testGetExperienceByIdWhenThrowsException() {
        when(experienceRepository.findById(experience.getId()))
                .thenThrow(new RuntimeException());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            experienceService.getExperienceById(experience.getId());
        });

        assertThat(exception.getMessage())
                .isEqualTo("Error Retrieving Experience ID: " + experience.getId());
        verify(experienceRepository).findById(experience.getId());
    }

    @Test
    public void testUpdateExperienceWhenSuccessful() {
        Experience updatedExperience = new Experience();
        updatedExperience.setCompany("New company");

        when(experienceRepository.findById(experience.getId()))
                .thenReturn(Optional.of(experience));
        when(experienceRepository.save(experience))
                .thenReturn(experience);

        Experience result =
                experienceService.updateExperience(experience.getId(), updatedExperience);

        assertThat(result).isNotNull();
        assertThat(result.getCompany())
                .isEqualTo(updatedExperience.getCompany());
        verify(experienceRepository).save(experience);
    }

    @Test
    public void testUpdateExperienceWhenNoRecordsFound() {
        when(experienceRepository.findById(experience.getId()))
                .thenReturn(Optional.empty());

        ExperienceNotFoundException exception = assertThrows(ExperienceNotFoundException.class, () -> {
           experienceService.updateExperience(experience.getId(), experience);
        });

        assertThat(exception.getMessage())
                .isEqualTo("Experience not found with ID: " + experience.getId());
        verify(experienceRepository).findById(experience.getId());
    }

    @Test
    public void testUpdateExperienceWhenThrowsException() {
        when(experienceRepository.findById(experience.getId()))
                .thenThrow(new RuntimeException());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            experienceService.updateExperience(experience.getId(), experience);
        });

        assertThat(exception.getMessage())
                .isEqualTo("Error Updating Experience");
        verify(experienceRepository).findById(experience.getId());
    }

    @Test
    public void testUpdateExperienceWhenAllFieldsUpdated() {
        Experience updatedExperience = new Experience();
        updatedExperience.setCompany("New Company");
        updatedExperience.setPosition("New Position");
        updatedExperience.setDateStarted(LocalDate.now().toString());
        updatedExperience.setDateEnded(LocalDate.now().toString());

        when(experienceRepository.findById(experience.getId()))
                .thenReturn(Optional.of(experience));
        when(experienceRepository.save(experience))
                .thenReturn(experience);

        Experience result =
                experienceService.updateExperience(experience.getId(), updatedExperience);

        assertThat(result).isNotNull();
        assertThat(result.getCompany())
                .isEqualTo(updatedExperience.getCompany());
        assertThat(result.getPosition())
                .isEqualTo(updatedExperience.getPosition());
        assertThat(result.getDateEnded())
                .isEqualTo(updatedExperience.getDateEnded());
        assertThat(result.getDateStarted())
                .isEqualTo(updatedExperience.getDateStarted());
        verify(experienceRepository).save(experience);
    }

    @Test
    public void testDeleteProjectWhenSuccessful() {
        when(experienceRepository.findById(experience.getId()))
                .thenReturn(Optional.of(experience));

        experienceService.deleteExperience(experience.getId());

        verify(experienceRepository).deleteById(experience.getId());
    }
}
