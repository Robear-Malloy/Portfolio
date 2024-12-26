package com.robear.portfolio.service.interfaces;

import com.robear.portfolio.model.Email;

public interface IEmailService {
    void sendPendingEmails();
    void sendContactEmail(Email email);
}
