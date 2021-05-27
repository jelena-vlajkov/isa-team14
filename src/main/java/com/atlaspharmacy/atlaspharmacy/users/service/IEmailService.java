package com.atlaspharmacy.atlaspharmacy.users.service;

import com.atlaspharmacy.atlaspharmacy.membershipinfo.domain.Complaint;
import com.atlaspharmacy.atlaspharmacy.promotions.domain.Promotion;
import com.atlaspharmacy.atlaspharmacy.reservations.domain.DrugReservation;
import com.atlaspharmacy.atlaspharmacy.users.DTO.EmailDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;

import javax.mail.MessagingException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface IEmailService {
    EmailDTO generateEmailInfo(Patient p);
    void sendConfirmationEmail(Patient p) throws FileNotFoundException, MessagingException, IOException;
    void answerToComplaint(Complaint c, String answer) throws FileNotFoundException, MessagingException, IOException;
    void sendPromotionNotification(Patient patient, Promotion promotion) throws FileNotFoundException, MessagingException, IOException;
    void sendDrugReservation(Patient patient, DrugReservation drugReservation) throws IOException, MessagingException;
}
