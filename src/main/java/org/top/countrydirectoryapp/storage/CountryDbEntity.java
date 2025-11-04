package org.top.countrydirectoryapp.storage;

import jakarta.persistence.*;
import org.top.countrydirectoryapp.model.Country;

// CountryDbEntity - сущность, описывающая запись в БД в таблице стран
@Entity
@Table(name = "country_t") // Имя таблицы в БД
public class CountryDbEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Автогенерируемый ID
    private Integer id;

    @Column(name = "short_name_f", nullable = false) // Поле в БД
    private String shortName;

    @Column(name = "full_name_f", nullable = false)
    private String fullName;

    @Column(name = "iso_alpha2_f", nullable = false, unique = true) // Уникальный индекс
    private String isoAlpha2;

    @Column(name = "iso_alpha3_f", nullable = false, unique = true) // Уникальный индекс
    private String isoAlpha3;

    @Column(name = "iso_numeric_f", nullable = false, unique = true) // Уникальный индекс
    private String isoNumeric;

    @Column(name = "population_f", nullable = false)
    private Long population;

    @Column(name = "square_f", nullable = false)
    private Long square;

    // Пустой конструктор для JPA
    public CountryDbEntity() {}

    // Конструктор из объекта Country
    public CountryDbEntity(Country country) {
        this.id = null; // ID генерируется БД
        this.shortName = country.getShortName();
        this.fullName = country.getFullName();
        this.isoAlpha2 = country.getIsoAlpha2();
        this.isoAlpha3 = country.getIsoAlpha3();
        this.isoNumeric = country.getIsoNumeric();
        this.population = country.getPopulation();
        this.square = country.getSquare();
    }

    // Геттеры и сеттеры
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getIsoAlpha2() {
        return isoAlpha2;
    }

    public void setIsoAlpha2(String isoAlpha2) {
        this.isoAlpha2 = isoAlpha2;
    }

    public String getIsoAlpha3() {
        return isoAlpha3;
    }

    public void setIsoAlpha3(String isoAlpha3) {
        this.isoAlpha3 = isoAlpha3;
    }

    public String getIsoNumeric() {
        return isoNumeric;
    }

    public void setIsoNumeric(String isoNumeric) {
        this.isoNumeric = isoNumeric;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public Long getSquare() {
        return square;
    }

    public void setSquare(Long square) {
        this.square = square;
    }

    // Метод для преобразования сущности БД обратно в объект Country
    public Country asCountry() {
        Country country = new Country();
        country.setShortName(this.shortName);
        country.setFullName(this.fullName);
        country.setIsoAlpha2(this.isoAlpha2);
        country.setIsoAlpha3(this.isoAlpha3);
        country.setIsoNumeric(this.isoNumeric);
        country.setPopulation(this.population);
        country.setSquare(this.square);
        return country;
    }
}