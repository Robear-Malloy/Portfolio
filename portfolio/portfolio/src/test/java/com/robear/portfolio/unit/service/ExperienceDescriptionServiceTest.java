package com.robear.portfolio.unit.service;

import com.robear.portfolio.exception.ExperienceDescriptionNotFoundException;
import com.robear.portfolio.model.ExperienceDescription;
import com.robear.portfolio.repository.ExperienceDescriptionRepository;
import com.robear.portfolio.service.ExperienceDescriptionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ExperienceDescriptionServiceTest {
    private final ExperienceDescription description = new ExperienceDescription();

    @InjectMocks
    private ExperienceDescriptionService descriptionService;

    @Mock
    private ExperienceDescriptionRepository descriptionRepository;

    @BeforeEach
    public void setup() {
        description.setId(1L);
        description.setDescription("Test Description");
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddDescriptionWhenSuccessful() {
        when(descriptionRepository.save(description)).thenReturn(description);

        ExperienceDescription result = descriptionService.addExperienceDescription(description);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(description);
        verify(descriptionRepository).save(description);
    }

    @Test
    public void testAddDescriptionWhenThrowsException() {
        when(descriptionRepository.save(description)).thenThrow(new RuntimeException(""));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            descriptionService.addExperienceDescription(description);
        });

        assertThat(exception.getMessage()).isEqualTo("Failed to add experience description.");
        verify(descriptionRepository).save(description);
    }

    @Test
    public void testGetDescriptionsWhenRecordsExist() {
        List<ExperienceDescription> descriptions = Collections.singletonList(description);
        when(descriptionRepository.findAllDescriptionByExperienceId(description.getId())).thenReturn(descriptions);

        List<ExperienceDescription> result = descriptionService.getExperienceDescriptions(description.getId());

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(descriptions);
        verify(descriptionRepository).findAllDescriptionByExperienceId(description.getId());
    }

    @Test
    public void testGetDescriptionsWhenRecordDoesNotExist() {
        when(descriptionRepository.findAllDescriptionByExperienceId(description.getId())).thenReturn(Collections.emptyList());

        ExperienceDescriptionNotFoundException exception = assertThrows(ExperienceDescriptionNotFoundException.class, () -> {
            descriptionService.getExperienceDescriptions(description.getId());
        });

        assertThat(exception.getMessage()).isEqualTo("No description found");
        verify(descriptionRepository).findAllDescriptionByExperienceId(description.getId());
    }

    @Test
    public void testUpdateDescriptionWhenSuccessful() {
        when(descriptionRepository.findById(description.getId())).thenReturn(Optional.of(description));
        when(descriptionRepository.save(description)).thenReturn(description);

        ExperienceDescription result = descriptionService.updateExperienceDescription(description);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(description);
        verify(descriptionRepository).save(description);
    }

    @Test
    public void testUpdateDescriptionWhenDoesNotExist() {
        when(descriptionRepository.findById(description.getId())).thenReturn(Optional.empty());

        ExperienceDescriptionNotFoundException exception = assertThrows(ExperienceDescriptionNotFoundException.class, () -> {
            descriptionService.updateExperienceDescription(description);
        });

        assertThat(exception.getMessage()).isEqualTo("No description found");
        verify(descriptionRepository).findById(description.getId());
    }

    @Test
    public void testDeleteDescriptionWhenSuccessful() {
        when(descriptionRepository.findById(description.getId())).thenReturn(Optional.of(description));

        descriptionService.deleteExperienceDescription(description);

        verify(descriptionRepository).deleteById(description.getId());
    }

    @Test
    public void testDeleteDescriptionWhenDoesNotExist() {
        when(descriptionRepository.findById(description.getId())).thenReturn(Optional.empty());

        ExperienceDescriptionNotFoundException exception = assertThrows(ExperienceDescriptionNotFoundException.class, () -> {
            descriptionService.deleteExperienceDescription(description);
        });

        assertThat(exception.getMessage()).isEqualTo("No description found");
        verify(descriptionRepository).findById(description.getId());
    }
}
