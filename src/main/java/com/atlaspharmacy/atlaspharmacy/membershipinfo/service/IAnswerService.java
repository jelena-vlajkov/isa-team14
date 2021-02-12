package com.atlaspharmacy.atlaspharmacy.membershipinfo.service;

import com.atlaspharmacy.atlaspharmacy.membershipinfo.DTO.AnsweredComplaintDTO;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.domain.AnswerToComplaint;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.service.impl.AnswerService;

import javax.mail.MessagingException;
import java.io.IOException;

public interface IAnswerService {
    AnswerToComplaint answer(AnsweredComplaintDTO answered) throws IOException, MessagingException;
}
