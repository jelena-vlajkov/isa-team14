package com.atlaspharmacy.atlaspharmacy.users.exceptions;

public class InvalidEmail extends  Exception{
    private static final String EMAIL_TAKEN = "Can't register user, email is already taken";
    public InvalidEmail(){super(EMAIL_TAKEN);}
    public InvalidEmail(String message){super(message);}
}
