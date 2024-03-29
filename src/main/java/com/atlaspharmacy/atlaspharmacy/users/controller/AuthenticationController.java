package com.atlaspharmacy.atlaspharmacy.users.controller;

import com.atlaspharmacy.atlaspharmacy.security.TokenUtils;
import com.atlaspharmacy.atlaspharmacy.security.auth.JwtAuthenticationRequest;
import com.atlaspharmacy.atlaspharmacy.security.domain.UserTokenState;
import com.atlaspharmacy.atlaspharmacy.users.DTO.AuthenticatedUserDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;
import com.atlaspharmacy.atlaspharmacy.users.domain.User;
import com.atlaspharmacy.atlaspharmacy.users.domain.enums.Role;
import com.atlaspharmacy.atlaspharmacy.users.service.impl.CustomDetailUserService;
import com.atlaspharmacy.atlaspharmacy.users.service.impl.PatientService;
import com.atlaspharmacy.atlaspharmacy.users.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private PatientService patientService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest,
                                                       HttpServletResponse response) {
        AuthenticatedUserDTO authenticatedUserDTO = new AuthenticatedUserDTO();
        User u = userService.getByEmail(authenticationRequest.getUsername());
        Patient patient = patientService.getByMail(authenticationRequest.getUsername());
        if(patient!=null){
            if(patient.getEnabled()){
                Authentication authentication = authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                                authenticationRequest.getPassword()));

                SecurityContext ctx = SecurityContextHolder.createEmptyContext();
                SecurityContextHolder.setContext(ctx);
                SecurityContextHolder.getContext().setAuthentication(authentication);

                User user = (User) authentication.getPrincipal();
                String jwt = tokenUtils.generateToken(user.getUsername());
                int expiresIn = tokenUtils.getExpiredIn();
                authenticatedUserDTO = new AuthenticatedUserDTO(user.getId(), user.getRole(), user.getUsername(), new UserTokenState(jwt, expiresIn), user.isFirstTimePassword());

                return new ResponseEntity<>(authenticatedUserDTO, HttpStatus.OK);
            }
        }else{
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()));

            SecurityContext ctx = SecurityContextHolder.createEmptyContext();
            SecurityContextHolder.setContext(ctx);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            User user = (User) authentication.getPrincipal();
            String jwt = tokenUtils.generateToken(user.getUsername());
            int expiresIn = tokenUtils.getExpiredIn();
            authenticatedUserDTO = new AuthenticatedUserDTO(user.getId(), user.getRole(), user.getUsername(), new UserTokenState(jwt, expiresIn), user.isFirstTimePassword());

            return new ResponseEntity<>(authenticatedUserDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(authenticatedUserDTO, HttpStatus.BAD_REQUEST);
    }


}
