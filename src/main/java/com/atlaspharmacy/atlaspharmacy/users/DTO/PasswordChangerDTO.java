package com.atlaspharmacy.atlaspharmacy.users.DTO;

public class PasswordChangerDTO {
    private Long user_id;
    public String oldpassword;
    public String newpassword;

    public PasswordChangerDTO() {
    }

    public PasswordChangerDTO(Long user_id, String oldpassword, String newpassword ) {
        this.user_id = user_id;
        this.oldpassword = oldpassword;
        this.newpassword = newpassword;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
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
