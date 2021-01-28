package com.atlaspharmacy.atlaspharmacy.reservations.exception;

public class DueDateSoonException extends Exception {
    public DueDateSoonException(String message) {
        super(message);
    }

    public DueDateSoonException() {
        super();
    }
}
