package com.atlaspharmacy.atlaspharmacy.membershipinfo.controller;

import com.atlaspharmacy.atlaspharmacy.customannotations.PatientAuthorization;
import com.atlaspharmacy.atlaspharmacy.customannotations.SystemAdminAuthorization;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.DTO.ComplaintDTO;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.DTO.PharmacyComplaintDTO;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.DTO.SubscriptionDTO;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.exceptions.AlreadySubscribedException;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.mapper.SubscriptionMapper;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.service.ISubscriptionService;
import com.atlaspharmacy.atlaspharmacy.reservations.exception.DueDateSoonException;
import com.atlaspharmacy.atlaspharmacy.schedule.exceptions.AppointmentNotFreeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/subscribe",produces = MediaType.APPLICATION_JSON_VALUE)
public class SubscriptionController {
    private final ISubscriptionService subscriptionService;
    @Autowired
    public SubscriptionController(ISubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PatientAuthorization
    public ResponseEntity<?> subscribe(@RequestBody SubscriptionDTO subscriptionDTO) throws AlreadySubscribedException {
        subscriptionService.subscribe(subscriptionDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping(value="/getAllUsers",produces = MediaType.APPLICATION_JSON_VALUE)
    @PatientAuthorization
    public List<SubscriptionDTO> getAllUsersSubscriptions(@RequestParam("userId") Long userId){
        return SubscriptionMapper.mapToDTOS(subscriptionService.getAllUsersSubscriptions(userId));
    }

    @PostMapping(value = "/remove", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PatientAuthorization
    public ResponseEntity<?> unsubscribe(@RequestBody SubscriptionDTO dto) throws AlreadySubscribedException {
        subscriptionService.unsubscribe(dto.getPatient().getId(), dto.getPharmacy().getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler(AlreadySubscribedException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    AlreadySubscribedException handleException(AlreadySubscribedException e) {
        return new AlreadySubscribedException();
    }

}
