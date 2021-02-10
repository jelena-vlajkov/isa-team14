package com.atlaspharmacy.atlaspharmacy.membershipinfo.exceptions;

public class AlreadySubscribedException extends Exception{
    private static final String WRONG_DATA = "Already subscribed";
    public AlreadySubscribedException(){super(WRONG_DATA);}
    public AlreadySubscribedException(String message){super(message);}
}
