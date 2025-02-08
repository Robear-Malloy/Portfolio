package com.robear.portfolio.unit.controller;

import com.robear.portfolio.controller.CertificationController;
import com.robear.portfolio.model.Certification;
import com.robear.portfolio.service.CertificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class CertificationControllerTest {
    @InjectMocks
    private CertificationController certificationController;

    @Mock
    private CertificationService certificationService;

    private final Certification certification = new Certification();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        certification.setId(1L);
        certification.setName("Test Certification");
        certification.setDateCompleted(LocalDate.of(2025, 2, 7));
        certification.setLanguage("en");
    }

    @Test
    public void testCreateCertificationWhenSuccessful() {
        when(certificationService.addCertification(certification)).thenReturn(certification);

        ResponseEntity<Certification> response = certificationController.createCertification(certification);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(certification.getId());
        verify(certificationService).addCertification(certification);
    }

    @Test
    public void testCreateCertificationWhenThrowsException() {
        when(certificationService.addCertification(certification)).thenThrow(new RuntimeException("Error"));

        ResponseEntity<Certification> response = certificationController.createCertification(certification);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        verify(certificationService).addCertification(certification);
    }

    @Test
    public void testGetAllCertificationsWhenSuccessful() {
        List<Certification> certifications = Collections.singletonList(certification);
        when(certificationService.getAllCertification(certification.getLanguage())).thenReturn(certifications);

        ResponseEntity<List<Certification>> response = certificationController.getAllCertifications(certification.getLanguage());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().get(0).getId()).isEqualTo(certification.getId());
        verify(certificationService).getAllCertification(certification.getLanguage());
    }

    @Test
    public void testGetAllCertificationsWhenThrowsException() {
        when(certificationService.getAllCertification(certification.getLanguage())).thenThrow(new RuntimeException("Error"));

        ResponseEntity<List<Certification>> response = certificationController.getAllCertifications(certification.getLanguage());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        verify(certificationService).getAllCertification(certification.getLanguage());
    }

    @Test
    public void testDeleteCertificationWhenSuccessful() {
        Long certificationId = 1L;

        doNothing().when(certificationService).deleteCertification(certificationId);

        ResponseEntity<Void> response = certificationController.deleteCertification(certificationId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(certificationService).deleteCertification(certificationId);
    }

    @Test
    public void testDeleteCertificationWhenThrowsException() {
        Long certificationId = 1L;

        doThrow(new RuntimeException("Error")).when(certificationService).deleteCertification(certificationId);

        ResponseEntity<Void> response = certificationController.deleteCertification(certificationId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        verify(certificationService).deleteCertification(certificationId);
    }
}
