package com.atlaspharmacy.atlaspharmacy.users.controller;

import com.atlaspharmacy.atlaspharmacy.users.DTO.EmailDTO;
import com.atlaspharmacy.atlaspharmacy.users.service.impl.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequestMapping(value="/email")
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

//    @PutMapping(value = "/sendEmail")
//    public ResponseEntity<?> sendMail(@RequestBody EmailDTO email) {
//        try {
//            String text = email.getBody() + email.getLink();
////            emailService.mailConfirmation(email.getPatientEmail(), email.getSubject(), email.getBody(), email.getLink());
//        } catch (NullPointerException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } catch (IllegalArgumentException e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @Async
//    @PutMapping(value = "/confirm")
//    public ResponseEntity<?> activation(@RequestBody EmailDTO email) {
//        try {
////            emailService.mailConfirmation(email.getPatientEmail(), email.getSubject(), email.getBody(),email.getLink());
//        } catch (NullPointerException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } catch (IllegalArgumentException e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
