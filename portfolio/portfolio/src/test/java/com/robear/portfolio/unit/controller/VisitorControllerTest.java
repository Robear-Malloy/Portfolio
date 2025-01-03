package com.robear.portfolio.unit.controller;

import com.robear.portfolio.controller.VisitorController;
import com.robear.portfolio.model.Visitor;
import com.robear.portfolio.service.VisitorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class VisitorControllerTest {
    @InjectMocks
    VisitorController visitorController;

    @Mock
    VisitorService visitorService;

    private final Visitor visitor = new Visitor();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        visitor.setId(1L);
        visitor.setName("John Doe");
        visitor.setMessage("Hello, World!");
    }

    @Test
    public void testCreateVisitorWhenSuccessful() {
        when(visitorService.addVisitor(visitor)).thenReturn(visitor);

        ResponseEntity<Visitor> response = visitorController.createVisitor(visitor);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(visitor.getId());
        verify(visitorService).addVisitor(visitor);
    }

    @Test
    public void testCreateVisitorWhenThrowsException() {
        when(visitorService.addVisitor(visitor)).thenThrow(new RuntimeException("Error"));

        ResponseEntity<Visitor> response = visitorController.createVisitor(visitor);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        verify(visitorService).addVisitor(visitor);
    }

    @Test
    public void testGetAllVisitorsWhenSuccessful() {
        List<Visitor> visitors = Collections.singletonList(visitor);
        when(visitorService.getAllVisitors()).thenReturn(visitors);

        ResponseEntity<List<Visitor>> response = visitorController.getAllVisitors();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().get(0).getId()).isEqualTo(visitor.getId());
        verify(visitorService).getAllVisitors();
    }

    @Test
    public void testGetAllVisitorsWhenThrowsException() {
        when(visitorService.getAllVisitors()).thenThrow(new RuntimeException("Error"));

        ResponseEntity<List<Visitor>> response = visitorController.getAllVisitors();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        verify(visitorService).getAllVisitors();
    }

    @Test
    public void testCreateAuthVisitorWhenSuccessful() {
        Visitor authVisitor = new Visitor();
        authVisitor.setId(1L);
        authVisitor.setName("John Doe");
        authVisitor.setMessage("[Authorized] Hello, World!");

        when(visitorService.addVisitor(any(Visitor.class))).thenReturn(authVisitor);

        ResponseEntity<Visitor> response = visitorController.createAuthVisitor(visitor);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getMessage()).contains("[Authorized]");
        verify(visitorService).addVisitor(any(Visitor.class));
    }

    @Test
    public void testCreateAuthVisitorWhenThrowsException() {
        when(visitorService.addVisitor(any(Visitor.class))).thenThrow(new RuntimeException("Error"));

        ResponseEntity<Visitor> response = visitorController.createAuthVisitor(visitor);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        verify(visitorService).addVisitor(any(Visitor.class));
    }
}
