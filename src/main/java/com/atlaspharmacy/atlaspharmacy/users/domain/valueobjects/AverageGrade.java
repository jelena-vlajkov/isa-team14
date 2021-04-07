package com.atlaspharmacy.atlaspharmacy.users.domain.valueobjects;

import javax.persistence.Embeddable;

@Embeddable
public class AverageGrade {
    private double excellent;
    private double veryGood;
    private double good;
    private double poor;
    private double veryPoor;

    public AverageGrade(){};

    public AverageGrade(double excellent, double veryGood, double good, double poor, double veryPoor) {
        this.excellent = 0;
        this.veryGood = 0;
        this.good = 0;
        this.poor = 0;
        this.veryPoor = 0;
    }

    public double count() {
        return ((5 * excellent) + (4 * veryGood) + (3 * good) + (2 * poor) + (1 * veryPoor))
                / (excellent + veryGood + good + poor + veryPoor);

    }
}
