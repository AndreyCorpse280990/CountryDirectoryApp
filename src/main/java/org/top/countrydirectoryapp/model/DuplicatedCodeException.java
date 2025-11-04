package org.top.countrydirectoryapp.model;

// DuplicatedCodeException - ошибка, когда код страны уже существует
public class DuplicatedCodeException extends RuntimeException {
    public DuplicatedCodeException(String code) {
        super("Country with code " + code + " already exists");
    }
}