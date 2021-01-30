package com.atlaspharmacy.atlaspharmacy.users.service;

import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;

import javax.mail.MessagingException;

public interface IEmailService {
    void sendEmail(Patient patient) throws MessagingException;
}
