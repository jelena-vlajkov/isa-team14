package com.atlaspharmacy.atlaspharmacy.reports.exceptions;

public class UnableToSaveReportException extends Exception {

    private static final String ERROR = "An internal error occured while saving your report.";

    public UnableToSaveReportException() {
        super(ERROR);
    }
}
