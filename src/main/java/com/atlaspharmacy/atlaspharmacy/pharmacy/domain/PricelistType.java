package com.atlaspharmacy.atlaspharmacy.pharmacy.domain;

public enum PricelistType {
    Counseling(Values.Counseling), Examination(Values.Examination);

    PricelistType(String value) {
        if (!this.name().equals(value))
            throw new IllegalArgumentException("Incorrect use of PricelistType!");
    }

    public static class Values {
        public static final String Counseling = "Counseling";
        public static final String Examination = "Examination";
    }
}
