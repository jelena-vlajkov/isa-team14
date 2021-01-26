package com.atlaspharmacy.atlaspharmacy.users.controller;

import com.atlaspharmacy.atlaspharmacy.users.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final IUserService _userService;

    @Autowired
    public UserController(IUserService userService) {
        _userService = userService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/proba")
    @PreAuthorize("hasRole('Dermatologist')")
    ResponseEntity<String> proba()
    {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
