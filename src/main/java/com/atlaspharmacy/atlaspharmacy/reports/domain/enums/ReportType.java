package com.atlaspharmacy.atlaspharmacy.reports.domain.enums;


public enum ReportType {
    ExaminationReport(Values.Examination), CounselingReport(Values.Counseling);
    ReportType(String value) {
        if (!this.name().equals(value))
            throw new IllegalArgumentException("Incorrect use of ReportType!");
    }

    public static class Values {
        public static final String Examination = "Examination";
        public static final String Counseling = "Counseling";
    }
}
