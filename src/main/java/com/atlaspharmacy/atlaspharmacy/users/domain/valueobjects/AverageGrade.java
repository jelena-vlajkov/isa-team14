package com.atlaspharmacy.atlaspharmacy.users.domain.valueobjects;

import javax.persistence.Embeddable;
import java.util.List;

@Embeddable
public class AverageGrade {
    private double Excellent ;
    private double VeryGood ;
    private double Good ;
    private double Poor ;
    private double VeryPoor;

    public AverageGrade(double excellent, double veryGood, double good, double poor, double veryPoor) {
        Excellent = 0;
        VeryGood = 0;
        Good = 0;
        Poor = 0;
        VeryPoor = 0;
    }

    public double count() {
        return ((5 * Excellent) + (4 * VeryGood) + (3 * Good) + (2 * Poor) + (1 * VeryPoor))
                / (Excellent + VeryGood + Good + Poor + VeryPoor);

    }
}
