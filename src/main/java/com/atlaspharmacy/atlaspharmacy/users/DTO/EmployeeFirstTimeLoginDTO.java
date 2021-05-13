package com.atlaspharmacy.atlaspharmacy.users.DTO;

public class EmployeeFirstTimeLoginDTO {

    private String email;
    private String repnewpassword;
    private String newpassword;

    public EmployeeFirstTimeLoginDTO() {
    }

    public EmployeeFirstTimeLoginDTO(String email, String repnewpassword, String newpassword) {
        this.email = email;
        this.repnewpassword = repnewpassword;
        this.newpassword = newpassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRepnewpassword() {
        return repnewpassword;
    }

    public void setRepnewpassword(String repnewpassword) {
        this.repnewpassword = repnewpassword;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }
}
