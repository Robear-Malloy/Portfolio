package com.robear.portfolio.unit.service;

import com.robear.portfolio.enums.TechType;
import com.robear.portfolio.service.TechStackService;
import com.robear.portfolio.exception.TechStackNotFoundException;
import com.robear.portfolio.exception.InvalidTechStackException;
import com.robear.portfolio.model.TechStack;
import com.robear.portfolio.repository.TechStackRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TechStackServiceTest {
    private final TechStack projectTechStack = new TechStack();
    private final TechStack experienceTechStack = new TechStack();

    @InjectMocks
    TechStackService techStackService;

    @Mock
    TechStackRepository techStackRepository;

    @BeforeEach
    public void setup() {
        projectTechStack.setId(1L);
        projectTechStack.setExperienceId(null);
        projectTechStack.setProjectId(1L);
        projectTechStack.setSkillId(1L);

        experienceTechStack.setId(2L);
        experienceTechStack.setExperienceId(1L);
        experienceTechStack.setProjectId(null);
        experienceTechStack.setSkillId(1L);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateTechStackWhenSuccessful() {
        when(techStackRepository.save(projectTechStack))
                .thenReturn(projectTechStack);

        TechStack result =
                techStackService.createTechStack(projectTechStack);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(projectTechStack);
        verify(techStackRepository).save(projectTechStack);
    }

    @Test
    public void testCreateTechStackWhenInvalid() {
        TechStack invalidTechStack = new TechStack();
        invalidTechStack.setId(1L);
        invalidTechStack.setExperienceId(1L);
        invalidTechStack.setProjectId(1L);
        invalidTechStack.setSkillId(1L);

        InvalidTechStackException exception = assertThrows(InvalidTechStackException.class, () -> {
            techStackService.createTechStack(invalidTechStack);
        });

        assertThat(exception.getMessage()).isEqualTo("Cannot Add Project/Experience");
        verify(techStackRepository, never()).save(invalidTechStack);
    }

    @Test
    public void testCreateTechStackWhenThrowsException() {
        when(techStackRepository.save(projectTechStack))
                .thenThrow(new RuntimeException(""));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            techStackService.createTechStack(projectTechStack);
        });

        assertThat(exception.getMessage())
                .isEqualTo("Error has occurred creating Tech Stack");
        verify(techStackRepository).save(projectTechStack);
    }

    //@Test
    //public void testGetTechStackWhenExperienceTechStackSuccessful() {
    //    List<TechStack> techStackList = Collections.singletonList(experienceTechStack);
    //    when(techStackRepository.findExperienceTechStack(experienceTechStack.getId()))
    //            .thenReturn(techStackList);
//
    //    List<TechStack> result =
    //            techStackService.getTechStack(experienceTechStack.getId(), TechType.EXPERIENCE);
//
    //    assertThat(result).isNotNull();
    //    assertThat(result).isEqualTo(techStackList);
    //    verify(techStackRepository).findExperienceTechStack(experienceTechStack.getId());
    //}

    @Test
    public void testGetTechStackWhenProjectTechStackSuccessful() {
        List<TechStack> techStackList = Collections.singletonList(projectTechStack);
        when(techStackRepository.findProjectTechStack(projectTechStack.getId()))
                .thenReturn(techStackList);

        List<TechStack> result =
                techStackService.getTechStack(projectTechStack.getId(), TechType.PROJECT);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(techStackList);
        verify(techStackRepository).findProjectTechStack(projectTechStack.getId());
    }

    @Test
    public void testGetTechStackWhenNoneFound() {
        when(techStackRepository.findExperienceTechStack(experienceTechStack.getId()))
                .thenReturn(Collections.emptyList());

        TechStackNotFoundException exception = assertThrows(TechStackNotFoundException.class, () -> {
            techStackService.getTechStack(experienceTechStack.getId(), TechType.EXPERIENCE);
        });

        assertThat(exception.getMessage())
                .isEqualTo("No Tech Stack found with ID:" + experienceTechStack.getId());
    }

    @Test
    public void testGetTechStackWhenThrowsException() {
        when(techStackRepository.findExperienceTechStack(experienceTechStack.getId()))
                .thenThrow(new RuntimeException(""));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
           techStackService.getTechStack(experienceTechStack.getId(), TechType.EXPERIENCE);
        });

        assertThat(exception.getMessage())
                .isEqualTo("Error occurred finding Tech Stack");
    }

    @Test
    public void testDeleteTechStackWhenSuccessful() {
        techStackService.deleteTechStack(projectTechStack.getId());

        verify(techStackRepository).deleteById(projectTechStack.getId());
    }
}
