package com.atlaspharmacy.atlaspharmacy.users.exceptions;

public class InvalidPassword extends Exception{
    private static final String DATA_NOT_PROPER = "Cant change password, old password doesnt match!";

    public  InvalidPassword() {
        super(DATA_NOT_PROPER);
    }

    public InvalidPassword(String message) {
        super(message);
    }
}
