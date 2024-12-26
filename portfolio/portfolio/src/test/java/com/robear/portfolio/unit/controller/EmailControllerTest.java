package com.robear.portfolio.unit.controller;

import com.robear.portfolio.controller.EmailController;
import com.robear.portfolio.service.EmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class EmailControllerTest {

    @InjectMocks
    private EmailController emailController;

    @Mock
    private EmailService emailService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSendEmailsWhenSuccessful() {
        doNothing().when(emailService).sendPendingEmails();

        ResponseEntity<Void> response = emailController.sendEmails();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(emailService, times(1)).sendPendingEmails();
    }

    @Test
    public void testSendEmailsWhenThrowsException() {
        doThrow(new RuntimeException("Error Sending Emails")).when(emailService).sendPendingEmails();

        ResponseEntity<Void> response = emailController.sendEmails();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        verify(emailService, times(1)).sendPendingEmails();
    }
}