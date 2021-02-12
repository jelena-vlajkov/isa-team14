package com.atlaspharmacy.atlaspharmacy.pharmacy.exceptions;

public class InvalidPharmacyData extends Exception{
    private static final String WRONG_DATA = "Can't register pharmacy, error in input";
    public InvalidPharmacyData(){super(WRONG_DATA);}
    public InvalidPharmacyData(String message){super(message);}
}
