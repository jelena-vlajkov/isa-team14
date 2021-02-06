package com.atlaspharmacy.atlaspharmacy.membershipinfo.service.impl;

import com.atlaspharmacy.atlaspharmacy.membershipinfo.DTO.AnsweredComplaintDTO;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.domain.AnswerToComplaint;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.mapper.AnswerToComplaintMapper;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.mapper.ComplaintMapper;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.repository.AnswerRepository;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.service.IAnswerService;
import com.atlaspharmacy.atlaspharmacy.users.service.impl.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;

@Service
public class AnswerService implements IAnswerService {
    private final AnswerRepository answerRepository;
    private final ComplaintService complaintService;
    private final EmailService emailService;
    @Autowired
    public AnswerService(AnswerRepository answerRepository, ComplaintService complaintService, EmailService emailService) {
        this.answerRepository = answerRepository;
        this.complaintService = complaintService;
        this.emailService = emailService;
    }

    @Override
    public AnswerToComplaint answer(AnsweredComplaintDTO answeredComplaintDTO) throws IOException, MessagingException {
        AnswerToComplaint answerprototype = AnswerToComplaintMapper.mapDTOToAnswer(answeredComplaintDTO);
        answerprototype.setComplaint(complaintService.getById(answeredComplaintDTO.getComplaint()));
        AnswerToComplaint answer = answerRepository.save(answerprototype);
        complaintService.answerToComplaint(answeredComplaintDTO.getComplaint());

        emailService.answerToComplaint(complaintService.getById(answeredComplaintDTO.getComplaint()), answeredComplaintDTO.getAnswer());
        return answer;
    }

}
