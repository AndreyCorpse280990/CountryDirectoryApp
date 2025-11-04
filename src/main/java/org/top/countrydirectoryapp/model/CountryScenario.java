package org.top.countrydirectoryapp.model;

import java.util.List;

public class CountryScenario {

    private final CountryStorage storage;

    public CountryScenario(CountryStorage storage) {
        this.storage = storage;
    }

    public List<Country> getAll() {
        return storage.selectAll();
    }

    public Country get(String code) {
        validateCode(code);
        Country country = storage.selectByCode(code);
        if (country == null) {
            throw new CountryNotFoundException(code);
        }
        return country;
    }

    // store - добавление новой страны в справочник
    // вход: объект страны для добавления с заполненными полями
    // выход: -
    // исключения:
    //  - InvalidCodeException - код переданной страны не является валидным кодом страны
    //  - DuplicatedCodeException - код переданной страны уже встречается в другой записи
    public void store(Country country) {
        validateCountryForStore(country);
        storage.insert(country); // Вызов метода в хранилище
    }

    public void edit(String code, Country country) {
        validateCode(code);
        validateCountryForEdit(country);
        storage.update(code, country);
    }

    public void delete(String code) {
        validateCode(code);
        storage.deleteByCode(code);
    }

    // validateCode - вспомогательный метод валидации кода
    private void validateCode(String code) {
        if (code == null) {
            throw new InvalidCodeException("null", "code is null");
        }
        if (!code.matches("[A-Za-z]{2}") && !code.matches("[A-Za-z]{3}") && !code.matches("\\d+")) {
            throw new InvalidCodeException(code, "code format is invalid. Expected 2-letter, 3-letter, or numeric code.");
        }
    }

    // validateCountryForStore - вспомогательный метод валидации страны перед сохранением
    private void validateCountryForStore(Country country) {
        // Проверка наименований
        if (country.getShortName() == null || country.getShortName().trim().isEmpty()) {
            throw new InvalidCodeException("shortName", "shortName cannot be null or empty");
        }
        if (country.getFullName() == null || country.getFullName().trim().isEmpty()) {
            throw new InvalidCodeException("fullName", "fullName cannot be null or empty");
        }

        // Проверка кодов (с использованием уже существующего метода validateCode)
        validateCode(country.getIsoAlpha2());
        validateCode(country.getIsoAlpha3());
        validateCode(country.getIsoNumeric());

        // Проверка population и square
        if (country.getPopulation() == null || country.getPopulation() < 0) {
            throw new InvalidCodeException("population", "population must be non-negative");
        }
        if (country.getSquare() == null || country.getSquare() < 0) {
            throw new InvalidCodeException("square", "square must be non-negative");
        }
    }

    // validateCountryForEdit - вспомогательный метод валидации страны перед редактированием
    private void validateCountryForEdit(Country country) {
        // Проверка population и square
        if (country.getPopulation() == null || country.getPopulation() < 0) {
            throw new InvalidCodeException("population", "population must be non-negative");
        }
        if (country.getSquare() == null || country.getSquare() < 0) {
            throw new InvalidCodeException("square", "square must be non-negative");
        }
        // Коды проверяются в storage.update
    }
}