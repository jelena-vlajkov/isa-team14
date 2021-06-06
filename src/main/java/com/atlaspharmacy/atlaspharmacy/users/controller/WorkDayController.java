package com.atlaspharmacy.atlaspharmacy.users.controller;

import com.atlaspharmacy.atlaspharmacy.customannotations.PharmacyAdminAuthorization;
import com.atlaspharmacy.atlaspharmacy.users.DTO.DermatologistDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.SupplierDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.WorkDayDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Supplier;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidEmail;
import com.atlaspharmacy.atlaspharmacy.users.mapper.DermatologistMapper;
import com.atlaspharmacy.atlaspharmacy.users.mapper.WorkDayMapper;
import com.atlaspharmacy.atlaspharmacy.users.service.IWorkDayService;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(allowedHeaders = "*",origins="*")
@RequestMapping(value = "/workDay")
public class WorkDayController {
    private final IWorkDayService workDayService;

    @Autowired
    public WorkDayController(IWorkDayService workDayService) {
        this.workDayService = workDayService;
    }

    @PostMapping(value = "/addWorkDay", consumes =  MediaType.APPLICATION_JSON_VALUE)
    @PharmacyAdminAuthorization
    public ResponseEntity<?> addWorkDay(@RequestBody WorkDayDTO workDayDTO) throws InvalidEmail, ParseException {
        try {
            workDayService.addWorkday(workDayDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/getByMedicalStaff", produces = MediaType.APPLICATION_JSON_VALUE)
    @PharmacyAdminAuthorization
    public @ResponseBody
    List<WorkDayDTO> getByMedicalStaff(@RequestParam("medicalStaffId") Long medicalStaffId){
        return  WorkDayMapper.mapToListDTO(workDayService.getBy(medicalStaffId));
    }

    @GetMapping(value = "/getUpcomingByStaff", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<WorkDayDTO> getUpcomingByStaff(@RequestParam("medicalStaffId") Long medicalStaffId, @RequestParam("pharmacyId") Long pharmacyId){
        return  WorkDayMapper.mapToListDTO(workDayService.getUpcomingStaff(medicalStaffId, pharmacyId));
    }


}
