package com.robear.portfolio.unit.service;

import com.robear.portfolio.model.Certification;
import com.robear.portfolio.repository.CertificationRepository;
import com.robear.portfolio.service.CertificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class CertificationServiceTest {

    @InjectMocks
    private CertificationService certificationService;

    @Mock
    private CertificationRepository certificationRepository;

    private final Certification certification = new Certification();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        certification.setId(1L);
        certification.setName("Test Certification");
        certification.setDateCompleted("01/01/2025");
    }

    @Test
    public void testAddCertificationWhenSuccessful() {
        when(certificationRepository.save(certification)).thenReturn(certification);

        Certification result = certificationService.addCertification(certification);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(certification.getId());
        verify(certificationRepository).save(certification);
    }

    @Test
    public void testAddCertificationWhenThrowsException() {
        when(certificationRepository.save(certification)).thenThrow(new RuntimeException("Error"));

        try {
            certificationService.addCertification(certification);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(RuntimeException.class);
            assertThat(e.getMessage()).isEqualTo("Error");
        }

        verify(certificationRepository).save(certification);
    }

    @Test
    public void testGetAllCertificationsWhenSuccessful() {
        List<Certification> certifications = Collections.singletonList(certification);
        when(certificationRepository.findAll()).thenReturn(certifications);

        List<Certification> result = certificationService.getAllCertification();

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getId()).isEqualTo(certification.getId());
        verify(certificationRepository).findAll();
    }

    @Test
    public void testGetAllCertificationsWhenThrowsException() {
        when(certificationRepository.findAll()).thenThrow(new RuntimeException("Error"));

        try {
            certificationService.getAllCertification();
        } catch (Exception e) {
            assertThat(e).isInstanceOf(RuntimeException.class);
            assertThat(e.getMessage()).isEqualTo("Error");
        }

        verify(certificationRepository).findAll();
    }

    @Test
    public void testDeleteCertificationWhenSuccessful() {
        Long certificationId = 1L;

        doNothing().when(certificationRepository).deleteById(certificationId);

        certificationService.deleteCertification(certificationId);

        verify(certificationRepository).deleteById(certificationId);
    }

    @Test
    public void testDeleteCertificationWhenThrowsException() {
        Long certificationId = 1L;

        doThrow(new RuntimeException("Error")).when(certificationRepository).deleteById(certificationId);

        try {
            certificationService.deleteCertification(certificationId);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(RuntimeException.class);
            assertThat(e.getMessage()).isEqualTo("Error");
        }

        verify(certificationRepository).deleteById(certificationId);
    }
}
