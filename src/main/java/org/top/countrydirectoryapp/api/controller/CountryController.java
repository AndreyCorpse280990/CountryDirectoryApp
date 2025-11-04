package org.top.countrydirectoryapp.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.top.countrydirectoryapp.model.CountryScenario;

// CountryController - контроллер для работы со странами
@RestController
@RequestMapping("/api/country")
public class CountryController {

    private final CountryScenario countries;

    public CountryController(CountryScenario countries) {
        this.countries = countries;
    }

    // обработчики операций со справочником стран будут добавлены позже
}