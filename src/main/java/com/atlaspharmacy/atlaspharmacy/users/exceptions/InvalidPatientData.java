package com.atlaspharmacy.atlaspharmacy.users.exceptions;

public class InvalidPatientData extends  Exception{
    private static final String DATA_NOT_PROPER = "Cant register patient, data is invalid";

    public  InvalidPatientData() {
        super(DATA_NOT_PROPER);
    }

    public InvalidPatientData(String message) {
        super(message);
    }

}
