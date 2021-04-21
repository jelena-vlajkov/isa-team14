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

    public AverageGrade(){
        Excellent = 0;
        VeryGood = 0;
        Good = 0;
        Poor = 0;
        VeryPoor = 0;
    }
    public AverageGrade(double excellent, double veryGood, double good, double poor, double veryPoor) {
        Excellent = excellent;
        VeryGood = veryGood;
        Good = good;
        Poor = poor;
        VeryPoor = veryPoor;
    }

    public double count() {
        return ((5 * Excellent) + (4 * VeryGood) + (3 * Good) + (2 * Poor) + (1 * VeryPoor))
                / (Excellent + VeryGood + Good + Poor + VeryPoor);

    }

    public double getExcellent() { return Excellent; }

    public void setExcellent(double excellent) { Excellent = excellent; }

    public double getVeryGood() {
        return VeryGood;
    }

    public void setVeryGood(double veryGood) {
        VeryGood = veryGood;
    }

    public double getGood() {
        return Good;
    }

    public void setGood(double good) {
        Good = good;
    }

    public double getPoor() {
        return Poor;
    }

    public void setPoor(double poor) {
        Poor = poor;
    }

    public double getVeryPoor() {
        return VeryPoor;
    }

    public void setVeryPoor(double veryPoor) {
        VeryPoor = veryPoor;
    }
}
