package com.atlaspharmacy.atlaspharmacy.users.DTO;

import com.atlaspharmacy.atlaspharmacy.security.domain.UserTokenState;

import javax.persistence.Id;

public class AuthenticatedUserDTO {
    @Id
    private Long Id;
    private String Role;
    private String Username;
    private UserTokenState Token;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public UserTokenState getToken() {
        return Token;
    }

    public void setToken(UserTokenState token) {
        Token = token;
    }

    public AuthenticatedUserDTO(Long id, String role, String username, UserTokenState token) {
        Id = id;
        Role = role;
        Username = username;
        Token = token;
    }
}
