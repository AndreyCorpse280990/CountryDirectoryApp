package org.top.countrydirectoryapp.model;

// CountryNotFoundException - ошибка, когда страна не найдена по коду
public class CountryNotFoundException extends RuntimeException {
    public CountryNotFoundException(String code) {
        super("Country with code " + code + " not found");
    }
}