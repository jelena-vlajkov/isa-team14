package com.atlaspharmacy.atlaspharmacy.users.controller;

import com.atlaspharmacy.atlaspharmacy.security.TokenUtils;
import com.atlaspharmacy.atlaspharmacy.security.auth.JwtAuthenticationRequest;
import com.atlaspharmacy.atlaspharmacy.security.domain.UserTokenState;
import com.atlaspharmacy.atlaspharmacy.users.DTO.AuthenticatedUserDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.User;
import com.atlaspharmacy.atlaspharmacy.users.service.impl.CustomDetailUserService;
import com.atlaspharmacy.atlaspharmacy.users.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomDetailUserService userDetailsService;

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/login")
    public ResponseEntity<AuthenticatedUserDTO> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest,
                                                                    HttpServletResponse response) {


        System.out.println(authenticationRequest.getPassword());
        System.out.println(authenticationRequest.getUsername());
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(user.getUsername());
        int expiresIn = tokenUtils.getExpiredIn();
        AuthenticatedUserDTO authenticatedUserDTO=new AuthenticatedUserDTO(user.getId(),user.getRole(),user.getUsername(),new UserTokenState(jwt, expiresIn));
        System.out.println(authenticatedUserDTO.getUsername());

        return ResponseEntity.ok(authenticatedUserDTO);
    }

}
