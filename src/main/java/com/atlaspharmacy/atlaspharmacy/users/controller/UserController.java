package com.atlaspharmacy.atlaspharmacy.users.controller;

import com.atlaspharmacy.atlaspharmacy.users.domain.User;
import com.atlaspharmacy.atlaspharmacy.users.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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


    @GetMapping(value = "/getUser", produces = MediaType.APPLICATION_JSON_VALUE)
//    @PreAuthorize("hasRole('DERMATOLOGIST')")
    public @ResponseBody
    User getUserById(@RequestParam("id") Long id) throws ParseException {
        return userService.getUserBy(id);
    }

}
