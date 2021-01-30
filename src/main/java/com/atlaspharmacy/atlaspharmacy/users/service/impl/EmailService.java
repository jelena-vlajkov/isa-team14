package com.atlaspharmacy.atlaspharmacy.users.service.impl;

import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;
import com.atlaspharmacy.atlaspharmacy.users.domain.VerificationToken;
import com.atlaspharmacy.atlaspharmacy.users.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService implements IEmailService {
    private final VerificationTokenService verificationTokenService;
    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;
    @Autowired
    public EmailService(VerificationTokenService verificationTokenService, TemplateEngine templateEngine, JavaMailSender javaMailSender) {
        this.verificationTokenService = verificationTokenService;
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(Patient patient) throws MessagingException{
        VerificationToken verificationToken = verificationTokenService.findByPatient(patient);
        if(verificationToken!=null){
            String token = verificationToken.getToken();
            Context context = new Context();
            context.setVariable("title", "Verify your email address");
            context.setVariable("link", "https://localhost:8080/activation?token="+token);

            String body = templateEngine.process("verification" , context);

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(patient.getEmail());
            helper.setSubject("Atlas pharmacy email address verification");
            helper.setText(body, true);
            javaMailSender.send(message);
        }
    }
}
