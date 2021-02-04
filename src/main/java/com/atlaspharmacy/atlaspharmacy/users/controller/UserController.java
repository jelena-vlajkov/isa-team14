package com.atlaspharmacy.atlaspharmacy.users.controller;

import com.atlaspharmacy.atlaspharmacy.users.DTO.UserDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.User;
import com.atlaspharmacy.atlaspharmacy.users.domain.enums.Role;
import com.atlaspharmacy.atlaspharmacy.users.mapper.UserMapper;
import com.atlaspharmacy.atlaspharmacy.users.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
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

    @GetMapping(value="/getLoggedIn", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    UserDTO getLoggedInUser() {
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String mail = ((User)user).getEmail();
        return UserMapper.mapToDTO(userService.getByEmail(mail));

    }

}
