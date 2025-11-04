package org.top.countrydirectoryapp.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.top.countrydirectoryapp.api.message.CommonApiMessages;
import org.top.countrydirectoryapp.model.*;

import java.util.List;

@RestController
@RequestMapping("/api/country")
public class CountryController {

    private final CountryScenario countries;

    public CountryController(CountryScenario countries) {
        this.countries = countries;
    }

    @GetMapping
    public List<Country> getAll() {
        return countries.getAll();
    }

    @GetMapping("{code}")
    public Country getById(@PathVariable String code) {
        return countries.get(code);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody Country country) {
        countries.store(country);
    }

    @PatchMapping("{code}")
    public Country edit(@PathVariable String code, @RequestBody Country country) {
        countries.edit(code, country);
        return country;
    }

    @DeleteMapping("{code}") // DELETE /api/country/{code}
    @ResponseStatus(HttpStatus.NO_CONTENT) // Возвращаем 204 No Content
    public void delete(@PathVariable String code) { // Принимаем код
        countries.delete(code);
    }

    // Обработчики исключений
    @ExceptionHandler(CountryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // Возвращаем 404
    public CommonApiMessages.ErrorMessage handleCountryNotFound(CountryNotFoundException e) {
        return new CommonApiMessages.ErrorMessage(e.getClass().getSimpleName(), e.getMessage());
    }

    @ExceptionHandler(DuplicatedCodeException.class)
    @ResponseStatus(HttpStatus.CONFLICT) // Возвращаем 409
    public CommonApiMessages.ErrorMessage handleDuplicatedCode(DuplicatedCodeException e) {
        return new CommonApiMessages.ErrorMessage(e.getClass().getSimpleName(), e.getMessage());
    }

    @ExceptionHandler(InvalidCodeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // Возвращаем 400
    public CommonApiMessages.ErrorMessage handleInvalidCode(InvalidCodeException e) {
        return new CommonApiMessages.ErrorMessage(e.getClass().getSimpleName(), e.getMessage());
    }
}