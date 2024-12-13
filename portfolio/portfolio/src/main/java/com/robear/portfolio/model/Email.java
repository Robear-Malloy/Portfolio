package com.robear.portfolio.model;

public class Email {
    private String subject;

    private String body;

    private String toEmail;

    private String fromEmail;

    public String getSubject() { return this.subject; }

    public void setSubject(String subject) { this.subject = subject; }

    public String getBody() { return this.body; }

    public void setBody(String body) { this.body = body; }

    public String getToEmail() { return this.toEmail; }

    public void setToEmail(String toEmail) { this.toEmail = toEmail; }

    public String getFromEmail() { return this.fromEmail; }

    public void setFromEmail(String fromEmail) { this.fromEmail = fromEmail; }
}
