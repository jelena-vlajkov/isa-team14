package com.atlaspharmacy.atlaspharmacy.users.controller;

import com.atlaspharmacy.atlaspharmacy.users.domain.Authority;
import com.atlaspharmacy.atlaspharmacy.users.domain.User;
import com.atlaspharmacy.atlaspharmacy.users.service.IAuthorityService;
import com.atlaspharmacy.atlaspharmacy.users.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@CrossOrigin

@RequestMapping(value = "/authscntr")
public class AuthorityController {
    private final IAuthorityService authorityService;
    @Autowired
    public AuthorityController(IAuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @GetMapping(value = "/getRolesAuths", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Authority> getAuthorityByRole(@RequestParam("role") String role) throws ParseException {
        return authorityService.getAllRolesAuthorities(role);
    }

}
