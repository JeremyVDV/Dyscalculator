package com.example.titan.dyscalculator;

/**
 * Created by stan on 8-6-2016.
 */
public class Convert {
    private String naam, soort;
    private double value;

    public Convert(String naam, double value, String soort){
        this.naam = naam;
        this.value = value;
        this.soort = soort;
    }

    public String getNaam() {
        return naam;
    }

    public String getSoort() {
        return soort;
    }

    public double getValue() {
        return value;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setSoort(String soort) {
        this.soort = soort;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String toString() {
        return naam;
    }
}
