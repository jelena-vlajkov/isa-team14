package com.atlaspharmacy.atlaspharmacy.users.service.impl;

import com.atlaspharmacy.atlaspharmacy.membershipinfo.domain.Complaint;
import com.atlaspharmacy.atlaspharmacy.reservations.domain.DrugReservation;
import com.atlaspharmacy.atlaspharmacy.promotions.domain.Promotion;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Appointment;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Counseling;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Examination;
import com.atlaspharmacy.atlaspharmacy.users.DTO.EmailDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.MedicalStaffDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.VacationRequestAnswerDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.VacationRequestDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;
import com.atlaspharmacy.atlaspharmacy.users.domain.Supplier;
import com.atlaspharmacy.atlaspharmacy.users.domain.VerificationToken;
import com.atlaspharmacy.atlaspharmacy.users.service.IEmailService;
import org.apache.tomcat.jni.Directory;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.jsoup.Jsoup;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Service
public class EmailService implements IEmailService {
    private final JavaMailSender javaMailSender;
    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public EmailDTO generateEmailInfo(Patient p) {

        String url = "http://localhost:4200/login?token="+p.getVerificationCode();
        String email = p.getEmail();
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setPatientEmail(email);
        emailDTO.setLink(url);
        emailDTO.setSubject("Confirm registration");

        return emailDTO;
    }

    public void sendMailForIssuingReservation(Patient p, DrugReservation drugReservation) throws IOException, MessagingException {

        String FilePath = "./reservationmail.html";
        File starting = new File(System.getProperty("user.dir"));
        File file = new File(starting,"src/main/java/com/atlaspharmacy/atlaspharmacy/reservations/service/reservationmail.html");



        Document doc = Jsoup.parse(file, "utf-8");

        MimeMessage message = javaMailSender.createMimeMessage();
        Multipart multiPart = new MimeMultipart("alternative");

        MimeBodyPart htmlPart = new MimeBodyPart();
        String body = doc.body().getElementsByTag("body").toString();
        body = body.replace("[medicationName]", drugReservation.getMedication().getName());
        body = body.replace("[name]", p.getName());

        htmlPart.setContent(body, "text/html; charset=utf-8");
        multiPart.addBodyPart(htmlPart);

        message.setContent(multiPart);
        message.setRecipients(Message.RecipientType.TO, p.getEmail());

        message.setSubject("Drug reservation picked up!");

        javaMailSender.send(message);
    }

    public void sendConfirmationEmail(Patient p) throws FileNotFoundException, MessagingException, IOException{
        EmailDTO emailDTO = generateEmailInfo(p);
        String FilePath = "./verification.html";
        File starting = new File(System.getProperty("user.dir"));
        File file = new File(starting,"src/main/java/com/atlaspharmacy/atlaspharmacy/users/service/impl/verification.html");



            Document doc = Jsoup.parse(file, "utf-8");

            MimeMessage message = javaMailSender.createMimeMessage();
            Multipart multiPart = new MimeMultipart("alternative");

            MimeBodyPart htmlPart = new MimeBodyPart();
            String body = doc.body().getElementsByTag("body").toString();
            body = body.replace("[link]", emailDTO.getLink());
            body = body.replace("[name]", p.getName());

            htmlPart.setContent(body, "text/html; charset=utf-8");
            multiPart.addBodyPart(htmlPart);

            message.setContent(multiPart);
            message.setRecipients(Message.RecipientType.TO, emailDTO.getPatientEmail());

            message.setSubject(emailDTO.getSubject());

            javaMailSender.send(message);


    }

    @Override
    public void answerToComplaint(Complaint c, String answer) throws FileNotFoundException, MessagingException, IOException {
        String FilePath = "./answertocomplaint.html";
        File starting = new File(System.getProperty("user.dir"));
        File file = new File(starting,"src/main/java/com/atlaspharmacy/atlaspharmacy/users/service/impl/answertocomplaint.html");

        Document doc = Jsoup.parse(file, "utf-8");

        MimeMessage message = javaMailSender.createMimeMessage();
        Multipart multiPart = new MimeMultipart("alternative");

        MimeBodyPart htmlPart = new MimeBodyPart();
        String body = doc.body().getElementsByTag("body").toString();
        body = body.replace("[answer]", answer);
        body = body.replace("[name]", c.getPatient().getName());

        htmlPart.setContent(body, "text/html; charset=utf-8");
        multiPart.addBodyPart(htmlPart);

        message.setContent(multiPart);
        message.setRecipients(Message.RecipientType.TO, c.getPatient().getEmail());

        message.setSubject("Answer to your complaint...");

        javaMailSender.send(message);
    }

    @Override
    public void sendNotificationToSupplier(Supplier supplier,boolean accepted) throws IOException, MessagingException {
        File starting = new File(System.getProperty("user.dir"));
        File file = new File(starting,"src/main/java/com/atlaspharmacy/atlaspharmacy/users/service/impl/notificationToSupplier.html");

        Document doc = Jsoup.parse(file, "utf-8");

        MimeMessage message = javaMailSender.createMimeMessage();
        Multipart multiPart = new MimeMultipart("alternative");

        MimeBodyPart htmlPart = new MimeBodyPart();
        String body = doc.body().getElementsByTag("body").toString();
        body = body.replace("[name]", supplier.getName());
        body = body.replace("[message]","There is a reply to you offer for our medication order.");
        if(accepted) {
            body=body.replace("[answer]"
                    ,"Congrats!We are happy to inform you that your offer has been accepted.");
        }
        else{
            body=body.replace("[answer]"
                    ,"We are sorry to inform you that your offer has been rejected.");

        }

        htmlPart.setContent(body, "text/html; charset=utf-8");
        multiPart.addBodyPart(htmlPart);

        message.setContent(multiPart);
        message.setRecipients(Message.RecipientType.TO, supplier.getEmail());

        message.setSubject("Medication order offer reply");

        javaMailSender.send(message);
    }
    @Override
    public void sendPromotionNotification(Patient patient, Promotion promotion) throws FileNotFoundException, MessagingException, IOException {
        File starting = new File(System.getProperty("user.dir"));
        File file = new File(starting,"src/main/java/com/atlaspharmacy/atlaspharmacy/users/service/impl/promotionNotification.html");

        Document doc = Jsoup.parse(file, "utf-8");

        MimeMessage message = javaMailSender.createMimeMessage();
        Multipart multiPart = new MimeMultipart("alternative");

        MimeBodyPart htmlPart = new MimeBodyPart();
        String body = doc.body().getElementsByTag("body").toString();
        body = body.replace("[name]", patient.getName());
        body = body.replace("[message]","There is a reply to you offer for our medication order.");
        body = body.replace("[pharmacyName]" , promotion.getPharmacy().getName());
        body=body.replace("[promotionBody]",promotion.getDescription());
        htmlPart.setContent(body, "text/html; charset=utf-8");
        multiPart.addBodyPart(htmlPart);

        message.setContent(multiPart);
        message.setRecipients(Message.RecipientType.TO, patient.getEmail());

        message.setSubject("New promotion notification");

        javaMailSender.send(message);
    }

    @Override
    public void sendDrugReservation(Patient patient, DrugReservation drugReservation) throws IOException, MessagingException {
        String FilePath = "./drugreservationmail.html";
        File starting = new File(System.getProperty("user.dir"));
        File file = new File(starting,"src/main/java/com/atlaspharmacy/atlaspharmacy/reservations/service/drugreservationmail.html");



        Document doc = Jsoup.parse(file, "utf-8");

        MimeMessage message = javaMailSender.createMimeMessage();
        Multipart multiPart = new MimeMultipart("alternative");

        int code = drugReservation.getUniqueIdentifier();

        MimeBodyPart htmlPart = new MimeBodyPart();
        String body = doc.body().getElementsByTag("body").toString();
        body = body.replace("[medicationName]", drugReservation.getMedication().getName());
        body = body.replace("[name]", patient.getName());
        body = body.replace("[code]", String.valueOf(code));

        htmlPart.setContent(body, "text/html; charset=utf-8");
        multiPart.addBodyPart(htmlPart);

        message.setContent(multiPart);
        message.setRecipients(Message.RecipientType.TO, patient.getEmail());

        message.setSubject("Created reservation!");

        javaMailSender.send(message);
    }

    @Override
    public void sendEmailForCanceledAppointmentDueVacation(Appointment c) throws IOException, MessagingException {
        File starting = new File(System.getProperty("user.dir"));
        File file = new File(starting,"src/main/java/com/atlaspharmacy/atlaspharmacy/users/service/impl/appointmentcanceled.html");

        Document doc = Jsoup.parse(file, "utf-8");

        MimeMessage message = javaMailSender.createMimeMessage();

        Multipart multiPart = new MimeMultipart("alternative");
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy. hh:mm");
        String startDate = dateFormat.format(c.getAppointmentPeriod().getStartTime());
        String endDate = dateFormat.format(c.getAppointmentPeriod().getEndTime());
        MimeBodyPart htmlPart = new MimeBodyPart();
        String body = doc.body().getElementsByTag("body").toString();
        body = body.replace("[name]", c.getPatient().getName());
        body = body.replace("[startDate]" , startDate);
        body=body.replace("[endDate]", endDate);
        htmlPart.setContent(body, "text/html; charset=utf-8");
        multiPart.addBodyPart(htmlPart);

        message.setContent(multiPart);
        message.setRecipients(Message.RecipientType.TO, c.getPatient().getEmail());

        message.setSubject("Appointment cancelation");

        javaMailSender.send(message);
    }

    @Override
    public void successfullyScheduledAppointment(Examination c) throws MessagingException, IOException {
        File starting = new File(System.getProperty("user.dir"));
        File file = new File(starting,"src/main/java/com/atlaspharmacy/atlaspharmacy/users/service/impl/successfullyscheduled.html");

        Document doc = Jsoup.parse(file, "utf-8");

        MimeMessage message = javaMailSender.createMimeMessage();

        Multipart multiPart = new MimeMultipart("alternative");
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy. hh:mm");
        String startDate = dateFormat.format(c.getAppointmentPeriod().getStartTime());
        String endDate = dateFormat.format(c.getAppointmentPeriod().getEndTime());
        MimeBodyPart htmlPart = new MimeBodyPart();
        String body = doc.body().getElementsByTag("body").toString();
        body = body.replace("[name]", c.getPatient().getName());
        body = body.replace("[startDate]" , startDate);
        body=body.replace("[pharmacyName]", c.getPharmacy().getName());
        body=body.replace("[endDate]", endDate);
        body = body.replace("[medicalStaffName]" , c.getDermatologist().getName());
        body=body.replace("[medicalStaffSurname]", c.getDermatologist().getSurname());
        htmlPart.setContent(body, "text/html; charset=utf-8");
        multiPart.addBodyPart(htmlPart);

        message.setContent(multiPart);
        message.setRecipients(Message.RecipientType.TO, c.getPatient().getEmail());

        message.setSubject("Appointment scheduled");

        javaMailSender.send(message);
    }

    @Override
    public void successfullyScheduledCounseling(Counseling c) throws MessagingException, IOException {
        File starting = new File(System.getProperty("user.dir"));
        File file = new File(starting,"src/main/java/com/atlaspharmacy/atlaspharmacy/users/service/impl/successfullyscheduled.html");

        Document doc = Jsoup.parse(file, "utf-8");

        MimeMessage message = javaMailSender.createMimeMessage();

        Multipart multiPart = new MimeMultipart("alternative");
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy. hh:mm");
        String startDate = dateFormat.format(c.getAppointmentPeriod().getStartTime());
        String endDate = dateFormat.format(c.getAppointmentPeriod().getEndTime());
        MimeBodyPart htmlPart = new MimeBodyPart();
        String body = doc.body().getElementsByTag("body").toString();
        body = body.replace("[name]", c.getPatient().getName());
        body = body.replace("[startDate]" , startDate);
        body=body.replace("[endDate]", endDate);
        body = body.replace("[medicalStaffName]" , c.getPharmacist().getName());
        body=body.replace("[medicalStaffSurname]", c.getPharmacist().getSurname());
        body=body.replace("[pharmacyName]", c.getPharmacy().getName());
        htmlPart.setContent(body, "text/html; charset=utf-8");
        multiPart.addBodyPart(htmlPart);

        message.setContent(multiPart);
        message.setRecipients(Message.RecipientType.TO, c.getPatient().getEmail());

        message.setSubject("Appointment scheduled");

        javaMailSender.send(message);
    }

    @Override
    public void sendVacationRequestAnswer(VacationRequestAnswerDTO answer) throws IOException, MessagingException {
        File starting = new File(System.getProperty("user.dir"));
        File file = new File(starting,"src/main/java/com/atlaspharmacy/atlaspharmacy/users/service/impl/vacationRequestAnswer.html");

        Document doc = Jsoup.parse(file, "utf-8");

        MimeMessage message = javaMailSender.createMimeMessage();
        Multipart multiPart = new MimeMultipart("alternative");

        MimeBodyPart htmlPart = new MimeBodyPart();
        String body = doc.body().getElementsByTag("body").toString();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy.");

        String startDate = format.format(answer.getVacationRequest().getStartDate());
        String endDate = format.format(answer.getVacationRequest().getEndDate());
        body = body.replace("[name]", answer.getVacationRequest().getMedicalStaff().getName());
        body = body.replace("[message]","There is a reply to you vacation request for pharmacy "+ answer.getVacationRequest().getPharmacy().getName()
                +"in period "+startDate+" - " +endDate);
        if(answer.isAccepted()) {
            body=body.replace("[answer]"
                    ,"Your request has been accepted. Enjoy your vacation.");
        }
        else{
            body=body.replace("[answer]"
                    ,"We are sorry to inform you that your request has been rejected.");
            body=body.replace("[explanation]"
                    ,answer.getExplanation());

        }

        htmlPart.setContent(body, "text/html; charset=utf-8");
        multiPart.addBodyPart(htmlPart);

        message.setContent(multiPart);
        message.setRecipients(Message.RecipientType.TO,answer.getVacationRequest().getMedicalStaff().getEmail());

        message.setSubject("Vacation request reply");

        javaMailSender.send(message);
    }
}
