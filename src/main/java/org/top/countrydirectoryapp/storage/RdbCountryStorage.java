package org.top.countrydirectoryapp.storage;

import org.springframework.stereotype.Repository;
import org.top.countrydirectoryapp.model.Country;
import org.top.countrydirectoryapp.model.CountryStorage;

import java.util.List;
import java.util.Optional;

// RdbCountryStorage - реализация CountryStorage для работы с реляционной БД
@Repository
public class RdbCountryStorage implements CountryStorage {

    // Репозиторий для работы с БД
    private final CountryRepository countryRepository;

    public RdbCountryStorage(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> selectAll() {
        throw new UnsupportedOperationException("implement me");
    }

    @Override
    public Optional<Country> selectByCode(String code) {
        throw new UnsupportedOperationException("implement me");
    }

    @Override
    public void insert(Country country) {
        throw new UnsupportedOperationException("implement me");
    }

    @Override
    public void deleteByCode(String code) {
        throw new UnsupportedOperationException("implement me");
    }

    @Override
    public void update(Country country) {
        throw new UnsupportedOperationException("implement me");
    }

    @Override
    public Optional<Country> selectByIsoAlpha2(String code) {
        throw new UnsupportedOperationException("implement me");
    }

    @Override
    public Optional<Country> selectByIsoAlpha3(String code) {
        throw new UnsupportedOperationException("implement me");
    }

    @Override
    public Optional<Country> selectByIsoNumeric(String code) {
        throw new UnsupportedOperationException("implement me");
    }
}