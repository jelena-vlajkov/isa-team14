package com.atlaspharmacy.atlaspharmacy.users.DTO;

public class EmailDTO {
    private String patientEmail;
    private String subject;
    private String body;
    private String link;

    public EmailDTO() {
    }

    public EmailDTO(String patientEmail, String subject, String body, String link) {
        this.patientEmail = patientEmail;
        this.subject = subject;
        this.body = body;
        this.link = link;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
