package com.atlaspharmacy.atlaspharmacy.schedule.exceptions;

public class AppointmentNotFreeException extends Exception {
    private final static String APPOINTMENT_NOT_FREE = "Can't schedule appointment in that specified time";
    public AppointmentNotFreeException() {
        super(APPOINTMENT_NOT_FREE);
    }

    public AppointmentNotFreeException(String message) {
        super(message);
    }
}
