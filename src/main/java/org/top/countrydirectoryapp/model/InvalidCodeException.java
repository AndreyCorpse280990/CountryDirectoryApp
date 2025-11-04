package org.top.countrydirectoryapp.model;

// InvalidCodeException - ошибка, когда код страны недопустим
public class InvalidCodeException extends RuntimeException {
    public InvalidCodeException(String code) {
        super(code + " is invalid");
    }

    public InvalidCodeException(String code, String details) {
        super(code + " is invalid: " + details);
    }
}