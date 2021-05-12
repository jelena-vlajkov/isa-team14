package com.atlaspharmacy.atlaspharmacy.users.DTO;

public class EmployeePassChange {

    private String email;
    public String oldpassword;
    public String newpassword;

    public EmployeePassChange(String email, String oldpassword, String newpassword) {
        this.email = email;
        this.oldpassword = oldpassword;
        this.newpassword = newpassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOldpassword() {
        return oldpassword;
    }

    public void setOldpassword(String oldpassword) {
        this.oldpassword = oldpassword;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }
}
