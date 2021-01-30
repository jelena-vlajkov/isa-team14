package com.atlaspharmacy.atlaspharmacy.users.service;

import com.atlaspharmacy.atlaspharmacy.users.DTO.EmailDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface IEmailService {
    EmailDTO generateEmailInfo(Patient p);
    void sendEmail(Patient p) throws FileNotFoundException, MessagingException, IOException;
}
