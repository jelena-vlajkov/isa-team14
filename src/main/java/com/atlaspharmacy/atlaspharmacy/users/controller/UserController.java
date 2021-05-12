package com.atlaspharmacy.atlaspharmacy.users.controller;


import com.atlaspharmacy.atlaspharmacy.customannotations.EmployeeAuthorization;
import com.atlaspharmacy.atlaspharmacy.customannotations.MedicalRecordAuthorization;
import com.atlaspharmacy.atlaspharmacy.users.DTO.EmployeePassChange;
import com.atlaspharmacy.atlaspharmacy.users.DTO.PharmDermDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.UserDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.User;
import com.atlaspharmacy.atlaspharmacy.users.mapper.UserMapper;
import com.atlaspharmacy.atlaspharmacy.users.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@CrossOrigin
public class UserController {
    private final IUserService userService;
    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/proba")
    @PreAuthorize("hasRole('DERMATOLOGIST')")
    ResponseEntity<String> proba()
    {
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/getUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @MedicalRecordAuthorization
    public @ResponseBody
    User getUserById(@RequestParam("id") Long id) throws ParseException {
        return userService.getUserBy(id);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/updateEmployee", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @EmployeeAuthorization
    public @ResponseBody
    ResponseEntity<String> updateEmployee(@RequestBody PharmDermDTO pharmDermDTO) throws ParseException {
        userService.updateEmployee(pharmDermDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/changeEmployeePass", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @EmployeeAuthorization
    public @ResponseBody
    ResponseEntity<String> updateEmployeePass(@RequestBody EmployeePassChange employeePassChange) throws Exception {
        userService.updateEmployeePassword(employeePassChange);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value="/getLoggedIn", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    UserDTO getLoggedInUser() {
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String mail = ((User)user).getEmail();
        User loggedIn = userService.getByEmail(mail);
        return UserMapper.mapToDTO(loggedIn);

    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    Exception handleException(Exception e) {
        return e;
    }


}
