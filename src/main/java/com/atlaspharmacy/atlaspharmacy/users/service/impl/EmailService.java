package com.atlaspharmacy.atlaspharmacy.users.service.impl;

import com.atlaspharmacy.atlaspharmacy.users.DTO.EmailDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;
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

@Service
public class EmailService implements IEmailService {
    private final JavaMailSender javaMailSender;
    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public EmailDTO generateEmailInfo(Patient p) {

        String url = "http://localhost:8088/patient/activation?user_id="+p.getId();
        String email = p.getEmail();
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setPatientEmail(email);
        emailDTO.setLink(url);
        emailDTO.setSubject("Confirm registration");

        return emailDTO;
    }

    public void sendEmail(Patient p) throws FileNotFoundException, MessagingException, IOException{
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
            htmlPart.setContent(body, "text/html; charset=utf-8");
            multiPart.addBodyPart(htmlPart);

            message.setContent(multiPart);
            message.setRecipients(Message.RecipientType.TO, emailDTO.getPatientEmail());

            message.setSubject(emailDTO.getSubject());

            javaMailSender.send(message);


    }

}
