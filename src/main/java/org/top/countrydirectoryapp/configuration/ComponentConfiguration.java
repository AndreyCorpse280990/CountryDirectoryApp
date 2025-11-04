package org.top.countrydirectoryapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.top.countrydirectoryapp.model.CountryScenario;
import org.top.countrydirectoryapp.storage.CountryRepository;
import org.top.countrydirectoryapp.storage.RdbCountryStorage;

@Configuration
public class ComponentConfiguration {

    private final CountryRepository countryRepository;

    public ComponentConfiguration(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Bean
    public CountryScenario countries() {
        return new CountryScenario(new RdbCountryStorage(countryRepository));
    }
}