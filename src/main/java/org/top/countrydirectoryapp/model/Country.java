package org.top.countrydirectoryapp.model;

public class Country {
    private String shortName;
    private String fullName;
    private String isoAlpha2;
    private String isoAlpha3;
    private String isoNumeric;
    private Long population;
    private Long square;

    public Country() {}

    public Country(String shortName, String fullName, String isoAlpha2, String isoAlpha3, String isoNumeric, Long population, Long square) {
        this.shortName = shortName;
        this.fullName = fullName;
        this.isoAlpha2 = isoAlpha2;
        this.isoAlpha3 = isoAlpha3;
        this.isoNumeric = isoNumeric;
        this.population = population;
        this.square = square;
    }

    // Геттеры и сеттеры
    public String getShortName() { return shortName; }
    public void setShortName(String shortName) { this.shortName = shortName; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getIsoAlpha2() { return isoAlpha2; }
    public void setIsoAlpha2(String isoAlpha2) { this.isoAlpha2 = isoAlpha2; }

    public String getIsoAlpha3() { return isoAlpha3; }
    public void setIsoAlpha3(String isoAlpha3) { this.isoAlpha3 = isoAlpha3; }

    public String getIsoNumeric() { return isoNumeric; }
    public void setIsoNumeric(String isoNumeric) { this.isoNumeric = isoNumeric; }

    public Long getPopulation() { return population; }
    public void setPopulation(Long population) { this.population = population; }

    public Long getSquare() { return square; }
    public void setSquare(Long square) { this.square = square; }
}