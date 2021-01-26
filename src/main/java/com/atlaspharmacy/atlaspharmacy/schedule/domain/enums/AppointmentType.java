package com.atlaspharmacy.atlaspharmacy.schedule.domain.enums;

public enum AppointmentType {
    Counseling(Values.Counseling), Examination(Values.Examination);

    AppointmentType(String value) {
        if (!this.name().equals(value))
            throw new IllegalArgumentException("Incorrect use of Role!");
    }

    public static class Values {
        public static final String Counseling = "Counseling";
        public static final String Examination = "Examination";
    }
}
