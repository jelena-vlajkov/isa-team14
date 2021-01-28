package com.atlaspharmacy.atlaspharmacy.notifications.controller;

import com.atlaspharmacy.atlaspharmacy.customannotations.NotificationAuthorization;
import com.atlaspharmacy.atlaspharmacy.notifications.DTO.MedicationNotificationDTO;
import com.atlaspharmacy.atlaspharmacy.notifications.mapper.NotificationMapper;
import com.atlaspharmacy.atlaspharmacy.notifications.service.INotificationService;
import com.atlaspharmacy.atlaspharmacy.reports.exceptions.UnableToSaveReportException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/notification", produces = MediaType.APPLICATION_JSON_VALUE)
public class NotificationController {
    private final INotificationService notificationService;

    @Autowired
    public NotificationController(INotificationService notificationService) {
        this.notificationService = notificationService;
    }


    @PostMapping(value = "/notifyPharmacyAdmin", produces = MediaType.APPLICATION_JSON_VALUE)
    @NotificationAuthorization
    public @ResponseBody
    ResponseEntity<?> notifyAdmin(@RequestBody MedicationNotificationDTO notificationDTO) {
        notificationService.medicationQuantityLow(NotificationMapper.mapMedicationFromDTO(notificationDTO),
                NotificationMapper.mapPharmacyFromDTO(notificationDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    Exception handleException(Exception e) {
        return new Exception();
    }




}
