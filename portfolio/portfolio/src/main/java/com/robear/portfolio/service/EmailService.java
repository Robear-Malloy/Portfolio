package com.robear.portfolio.service;

import com.robear.portfolio.model.Contact;
import com.robear.portfolio.model.Email;
import com.robear.portfolio.service.interfaces.IEmailService;
import com.robear.portfolio.util.EmailHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService implements IEmailService {
    private static Logger logger = LoggerFactory.getLogger(EmailService.class);
    private final ContactService contactService;
    private final EmailHelper emailHelper;

    @Autowired
    public EmailService(ContactService contactService, EmailHelper emailHelper) {
        this.contactService = contactService;
        this.emailHelper = emailHelper;
    }

    @Override
    public void sendPendingEmails() {
        try {
            logger.info("Sending Myself Email of Pending Contacts");
            List<Contact> pendingContacts = contactService.getPendingContacts();

            StringBuilder body = new StringBuilder();
            body.append("<html><body>");
            body.append("<h1>Pending Contacts</h1>");
            body.append("<table border='1' style='border-collapse: collapse; width: 100%;'>");
            body.append("<tr>")
                    .append("<th>Name</th>")
                    .append("<th>Email</th>")
                    .append("<th>Company</th>")
                    .append("<th>Description</th>")
                    .append("<th>Date Sent</th>")
                    .append("</tr>");

            for (Contact contact : pendingContacts) {
                body.append("<tr>")
                        .append("<td>").append(contact.getName()).append("</td>")
                        .append("<td>").append(contact.getEmail()).append("</td>")
                        .append("<td>").append(contact.getCompany()).append("</td>")
                        .append("<td>").append(contact.getDescription()).append("</td>")
                        .append("<td>").append(contact.getDateSent()).append("</td>")
                        .append("</tr>");
            }

            body.append("</table>");
            body.append("</body></html>");

            Email email = new Email();
            email.setSubject("[IMPORTANT]: Respond to Portfolio Contacts");
            email.setBody(body.toString());
            emailHelper.sendEmail(email);

        } catch (Exception e) {
            logger.error("Error Sending pending contacts email");
            throw new RuntimeException();
        }
    }

    @Override
    public void sendContactEmail(Email email) {
        try {
            logger.info("Email was sent from Contact Section");

            if (email.getSubject() == null || email.getSubject().isEmpty()) {
                email.setSubject("No Subject Provided");
            }

            if (email.getFromEmail() == null || email.getFromEmail().isEmpty()) {
                email.setSubject("No Email Provided");
            }

            StringBuilder body = new StringBuilder();
            body.append("The Following Email Sent From Portfolio Website\n\n");
            body.append("Name: ").append(email.getName()).append("\n");
            body.append("Email: ").append(email.getFromEmail()).append("\n\n");
            body.append("Message:\n").append(email.getBody()).append("\n");

            StringBuilder subject = new StringBuilder();
            subject.append("[PORTFOLIO]: ").append(email.getSubject());

            email.setSubject(subject.toString());
            email.setBody(body.toString());
            emailHelper.sendEmail(email);

        } catch (Exception e) {
            logger.error("Error sending contact email.");
            throw new RuntimeException();
        }
    }

    public void sendTestEmail() {
        Email email = new Email();
        email.setSubject("Test Portfolio Email");
        email.setBody("Test Text in Body");
        emailHelper.sendEmail(email);
    }
}
