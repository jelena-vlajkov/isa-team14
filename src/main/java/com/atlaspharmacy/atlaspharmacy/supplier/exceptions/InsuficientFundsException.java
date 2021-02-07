package com.atlaspharmacy.atlaspharmacy.supplier.exceptions;

public class InsuficientFundsException extends Exception{
    public InsuficientFundsException(String message) {
        super(message);
    }

    public InsuficientFundsException() {
        super();
    }
}
