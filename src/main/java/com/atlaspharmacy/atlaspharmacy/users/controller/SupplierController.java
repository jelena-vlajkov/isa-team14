package com.atlaspharmacy.atlaspharmacy.users.controller;

import com.atlaspharmacy.atlaspharmacy.users.DTO.PasswordChangerDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.SupplierDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.SystemAdminDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Supplier;
import com.atlaspharmacy.atlaspharmacy.users.domain.SystemAdmin;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidPassword;
import com.atlaspharmacy.atlaspharmacy.users.service.ISupplierService;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@CrossOrigin
@RequestMapping(value = "/supplier")
public class SupplierController {
    private final ISupplierService supplierService;

    @Autowired
    public SupplierController(ISupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping(value = "/add", consumes =  MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('SYSADMIN')")
    public ResponseEntity<?> registerSupplier(@RequestBody SupplierDTO supplierDTO) throws InvalidEmail, ParseException {
        try {
            supplierDTO.setRole("Supplier");
            Supplier s = supplierService.registerSupplier(supplierDTO);

        } catch (InvalidEmail email) {
            email.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/getById", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getById(@RequestParam("id") Long id) throws Exception {
        return new ResponseEntity<>(supplierService.getById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_SUPPLIER')")
    @PostMapping(value = "/update", consumes =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateSupplier(@RequestBody SupplierDTO supplierDTO) throws InvalidEmail , ParseException{
        Supplier s = supplierService.updateSupplier(supplierDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('SUPPLIER')")
    @PostMapping(value = "/changepassword", consumes =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changePassword(@RequestBody PasswordChangerDTO passwordChangerDTO) throws InvalidEmail, ParseException, InvalidPassword {
        if(supplierService.changePassword(passwordChangerDTO.getOldpassword(), passwordChangerDTO.getNewpassword())){
            return new ResponseEntity<>(HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);



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
