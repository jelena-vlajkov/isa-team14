package com.atlaspharmacy.atlaspharmacy.users.controller;

import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;
import com.atlaspharmacy.atlaspharmacy.users.domain.User;
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
    @PreAuthorize("hasRole('DERMATOLOGIST') || hasRole('PATIENT ')")
    public @ResponseBody
    User getUserById(@RequestParam("id") Long id) throws ParseException {
        return userService.getUserBy(id);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/getLoggedUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    //@PreAuthorize("hasRole('DERMATOLOGIST') || hasRole('PATIENT ')")
    public @ResponseBody
    User getLoggedUser() throws ParseException {
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String mail = ((User)user).getEmail();
        return userService.getUserByMail(mail);

    }



}
