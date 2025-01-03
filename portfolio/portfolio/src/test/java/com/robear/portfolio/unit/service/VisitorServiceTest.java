package com.robear.portfolio.unit.service;

import com.robear.portfolio.model.Visitor;
import com.robear.portfolio.repository.VisitorRepository;
import com.robear.portfolio.service.VisitorService;
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

public class VisitorServiceTest {
    @InjectMocks
    VisitorService visitorService;

    @Mock
    VisitorRepository visitorRepository;

    private final Visitor visitor = new Visitor();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        visitor.setId(1L);
        visitor.setName("John Doe");
        visitor.setMessage("Hello, World!");
    }

    @Test
    public void testAddVisitorWhenSuccessful() {
        when(visitorRepository.save(visitor)).thenReturn(visitor);

        Visitor result = visitorService.addVisitor(visitor);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(visitor.getId());
        verify(visitorRepository).save(visitor);
    }

    @Test
    public void testAddVisitorWhenThrowsException() {
        when(visitorRepository.save(visitor)).thenThrow(new RuntimeException("Database Error"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            visitorService.addVisitor(visitor);
        });

        assertThat(exception.getMessage()).contains("Database Error");
        verify(visitorRepository).save(visitor);
    }

    @Test
    public void testGetAllVisitorsWhenSuccessful() {
        List<Visitor> visitors = Collections.singletonList(visitor);
        when(visitorRepository.findAll()).thenReturn(visitors);

        List<Visitor> result = visitorService.getAllVisitors();

        assertThat(result).isNotNull();
        assertThat(result.get(0).getId()).isEqualTo(visitor.getId());
        verify(visitorRepository).findAll();
    }

    @Test
    public void testGetAllVisitorsWhenNoneFound() {
        when(visitorRepository.findAll()).thenReturn(Collections.emptyList());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            visitorService.getAllVisitors();
        });

        assertThat(exception.getMessage()).contains("None Found");
        verify(visitorRepository).findAll();
    }

    @Test
    public void testGetAllVisitorsWhenThrowsException() {
        when(visitorRepository.findAll()).thenThrow(new RuntimeException("Error retrieving visitors"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            visitorService.getAllVisitors();
        });

        assertThat(exception.getMessage()).contains("Error retrieving visitors");
        verify(visitorRepository).findAll();
    }
}