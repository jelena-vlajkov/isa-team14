package com.atlaspharmacy.atlaspharmacy.users.DTO;

public class AuthenticationDTO {

    private String Username;
    private String Password;

    public AuthenticationDTO() { }
    public AuthenticationDTO(String username, String password)
    {
            Username = username;
            Password = password;
    }
    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
