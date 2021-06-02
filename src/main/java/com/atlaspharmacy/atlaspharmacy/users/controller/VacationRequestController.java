package com.atlaspharmacy.atlaspharmacy.users.controller;

import com.atlaspharmacy.atlaspharmacy.customannotations.AppointmentAuthorization;
import com.atlaspharmacy.atlaspharmacy.customannotations.EmployeeAuthorization;
import com.atlaspharmacy.atlaspharmacy.customannotations.PharmacyAdminAuthorization;
import com.atlaspharmacy.atlaspharmacy.users.DTO.VacationRequestAnswerDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.VacationRequestDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.VacationRequest;
import com.atlaspharmacy.atlaspharmacy.users.mapper.VacationRequestMapper;
import com.atlaspharmacy.atlaspharmacy.users.service.IVacationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value="/vacationRequest")
public class VacationRequestController {

    private final IVacationRequestService vacationRequestService;

    @Autowired
    public VacationRequestController(IVacationRequestService vacationRequestService) {
        this.vacationRequestService = vacationRequestService;
    }

    @PostMapping(value = "/saveVacationRequest", produces = MediaType.APPLICATION_JSON_VALUE)
    @EmployeeAuthorization
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<?> saveNewVacationRequest(@RequestBody VacationRequestDTO vacationRequestDTO) throws Exception {
        vacationRequestService.saveVacationRequest(vacationRequestDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/getByPharmacy")
    @PharmacyAdminAuthorization
    public List<VacationRequestDTO> getByPharmacy(@RequestParam("pharmacyId") Long pharmacyId) throws Exception {
        return VacationRequestMapper.mapToListDTOS(vacationRequestService.getAllByPharmacy(pharmacyId));
    }
    @PostMapping(value = "/approveVacationRequest", produces = MediaType.APPLICATION_JSON_VALUE)
    @PharmacyAdminAuthorization
    public ResponseEntity<?> approveVacationRequest(@RequestBody VacationRequestAnswerDTO answer) throws Exception {
        try{
            vacationRequestService.approveVacationRequest(answer);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/denyVacationRequest", produces = MediaType.APPLICATION_JSON_VALUE)
    @PharmacyAdminAuthorization
    public ResponseEntity<?> denyVacationRequest(@RequestBody VacationRequestAnswerDTO answer) throws Exception {
        try{
            vacationRequestService.denyVacationRequest(answer);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    Exception handleException(Exception e) {
        return e;
    }

}
