package com.robear.portfolio.util;

import com.robear.portfolio.config.AppConfig;
import com.robear.portfolio.model.Email;
import org.springframework.stereotype.Component;

@Component
public class EmailHelper {
    private final AppConfig appConfig;

    public EmailHelper(AppConfig appConfig) {this.appConfig = appConfig; }

    public void sendEmail(Email email) {
        email.setFromEmail(appConfig.CURRENT_EMAIL);
        email.setToEmail(appConfig.CURRENT_EMAIL);
    }
}
