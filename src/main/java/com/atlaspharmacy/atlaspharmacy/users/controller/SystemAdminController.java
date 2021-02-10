package com.atlaspharmacy.atlaspharmacy.users.controller;

import com.atlaspharmacy.atlaspharmacy.customannotations.AppointmentAuthorization;
import com.atlaspharmacy.atlaspharmacy.customannotations.SystemAdminAuthorization;
import com.atlaspharmacy.atlaspharmacy.users.DTO.PasswordChangerDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.SystemAdminDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.SystemAdmin;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidEmail;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidPassword;
import com.atlaspharmacy.atlaspharmacy.users.service.ISystemAdminService;
import com.atlaspharmacy.atlaspharmacy.users.service.impl.SystemAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@CrossOrigin
@RequestMapping(value = "/admin")
public class SystemAdminController {
    private final ISystemAdminService systemAdminService;

    @Autowired
    public SystemAdminController(SystemAdminService systemAdminService) {
        this.systemAdminService = systemAdminService;
    }

    @PreAuthorize("hasRole('SYSADMIN')")
    @PostMapping(value = "/add", consumes =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerSystemAdmin(@RequestBody SystemAdminDTO systemAdminDTO) throws InvalidEmail , ParseException{
        try {
            systemAdminDTO.setSysRole("SysAdmin");
            SystemAdmin s = systemAdminService.registerSysAdmin(systemAdminDTO);

        } catch (InvalidEmail email) {
            email.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/getById", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getById(@RequestParam("id") Long id) throws Exception {
        return new ResponseEntity<>(systemAdminService.getById(id), HttpStatus.OK);
    }

    @SystemAdminAuthorization
    @PostMapping(value = "/update", consumes =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateSystemAdmin(@RequestBody SystemAdminDTO systemAdminDTO) throws InvalidEmail , ParseException{
            SystemAdmin s = systemAdminService.updateSystemAdmin(systemAdminDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_SYSADMIN')")
    @PostMapping(value = "/changepassword", consumes =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changePassword(@RequestBody PasswordChangerDTO passwordChangerDTO){
        if(systemAdminService.changePassword(passwordChangerDTO.getOldpassword(), passwordChangerDTO.getNewpassword())){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPassword.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    InvalidPassword InvalidPassword(InvalidPassword e) {
        return new InvalidPassword();
    }

    @ExceptionHandler(InvalidEmail.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    InvalidEmail handleException(InvalidEmail e) {
        return new InvalidEmail();
    }

    @ExceptionHandler(ParseException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ParseException handleException(ParseException e) {
        return new ParseException("Error while parsing values", 0);
    }


}
