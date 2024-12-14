package com.robear.portfolio.util;

import com.robear.portfolio.config.AppConfig;
import com.robear.portfolio.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Component
public class EmailHelper {
    private final AppConfig appConfig;

    @Autowired
    private JavaMailSender javaMailSender;

    public EmailHelper(AppConfig appConfig) {this.appConfig = appConfig; }

    public void sendEmail(Email email) {
        try {
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();

            mailMessage.setFrom(appConfig.PORTFOLIO_EMAIL);
            mailMessage.setTo(appConfig.PERSONAL_EMAIL);
            mailMessage.setText(email.getBody());
            mailMessage.setSubject(email.getSubject());

            javaMailSender.send(mailMessage);
        }
        catch (Exception e) {
            throw new RuntimeException("Unexpected Error: Email Failed to Send");
        }
    }
}
