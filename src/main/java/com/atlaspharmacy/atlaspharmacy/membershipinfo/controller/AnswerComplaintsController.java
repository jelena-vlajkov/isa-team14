package com.atlaspharmacy.atlaspharmacy.membershipinfo.controller;

import com.atlaspharmacy.atlaspharmacy.customannotations.PatientAuthorization;
import com.atlaspharmacy.atlaspharmacy.customannotations.SystemAdminAuthorization;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.DTO.AnsweredComplaintDTO;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.DTO.ComplaintDTO;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.service.IAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping(value = "/answerComplaint",produces = MediaType.APPLICATION_JSON_VALUE)
public class AnswerComplaintsController {
    private final IAnswerService answerService;
    @Autowired
    public AnswerComplaintsController(IAnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @SystemAdminAuthorization
    public ResponseEntity<?> answer(@RequestBody AnsweredComplaintDTO answeredComplaintDTO) throws IOException, MessagingException {
        answerService.answer(answeredComplaintDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
