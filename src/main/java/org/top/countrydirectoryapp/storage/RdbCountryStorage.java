package org.top.countrydirectoryapp.storage;

import org.springframework.stereotype.Repository;
import org.top.countrydirectoryapp.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RdbCountryStorage implements CountryStorage {

    private final CountryRepository countryRepository;

    public RdbCountryStorage(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> selectAll() {
        List<CountryDbEntity> entities = countryRepository.findAll();
        List<Country> countries = new ArrayList<>();
        for (CountryDbEntity entity : entities) {
            countries.add(entity.asCountry());
        }
        return countries;
    }

    @Override
    public Country selectByCode(String code) {
        Optional<CountryDbEntity> entityOpt = Optional.empty();
        if (isNumeric(code)) {
            entityOpt = countryRepository.findByIsoNumeric(code);
        } else if (code.length() == 2) {
            entityOpt = countryRepository.findByIsoAlpha2(code);
        } else if (code.length() == 3) {
            entityOpt = countryRepository.findByIsoAlpha3(code);
        }

        if (entityOpt.isPresent()) {
            return entityOpt.get().asCountry();
        }
        return null; // Возвращаем null, если не найдено
    }

    @Override
    public void insert(Country country) {
        // Проверки уникальности кодов и названий
        if (countryRepository.findByIsoAlpha2(country.getIsoAlpha2()).isPresent() ||
                countryRepository.findByIsoAlpha3(country.getIsoAlpha3()).isPresent() ||
                countryRepository.findByIsoNumeric(country.getIsoNumeric()).isPresent()) {
            // Бросаем исключение, если один из кодов уже существует
            String duplicateCode = country.getIsoAlpha2();
            if (countryRepository.findByIsoAlpha2(country.getIsoAlpha2()).isPresent()) duplicateCode = country.getIsoAlpha2();
            else if (countryRepository.findByIsoAlpha3(country.getIsoAlpha3()).isPresent()) duplicateCode = country.getIsoAlpha3();
            else if (countryRepository.findByIsoNumeric(country.getIsoNumeric()).isPresent()) duplicateCode = country.getIsoNumeric();
            throw new DuplicatedCodeException(duplicateCode);
        }
        // Проверка уникальности названий (если задание требует)
        // Optional<CountryDbEntity> existingByName = countryRepository.findByShortName(country.getShortName()); // Добавь метод в репозиторий
        // if (existingByName.isPresent()) throw new DuplicatedNameException(country.getShortName()); // Добавь исключение

        CountryDbEntity entity = new CountryDbEntity(country);
        countryRepository.save(entity);
    }

    @Override
    public void update(String code, Country country) {
        // Найдём старую запись по коду
        Country oldCountry = selectByCode(code);
        if (oldCountry == null) {
            throw new CountryNotFoundException(code);
        }

        // Проверим, не меняются ли коды
        if (!country.getIsoAlpha2().equals(oldCountry.getIsoAlpha2()) ||
                !country.getIsoAlpha3().equals(oldCountry.getIsoAlpha3()) ||
                !country.getIsoNumeric().equals(oldCountry.getIsoNumeric())) {
            throw new InvalidCodeException(code, "Country codes cannot be changed during update");
        }

        // Проверим, не дублируются ли названия/коды с другими записями
        Optional<CountryDbEntity> existingByAlpha2 = countryRepository.findByIsoAlpha2(country.getIsoAlpha2());
        Optional<CountryDbEntity> existingByAlpha3 = countryRepository.findByIsoAlpha3(country.getIsoAlpha3());
        Optional<CountryDbEntity> existingByNumeric = countryRepository.findByIsoNumeric(country.getIsoNumeric());

        if (existingByAlpha2.isPresent() && !existingByAlpha2.get().getIsoAlpha2().equals(oldCountry.getIsoAlpha2())) {
            throw new DuplicatedCodeException(country.getIsoAlpha2());
        }
        if (existingByAlpha3.isPresent() && !existingByAlpha3.get().getIsoAlpha3().equals(oldCountry.getIsoAlpha3())) {
            throw new DuplicatedCodeException(country.getIsoAlpha3());
        }
        if (existingByNumeric.isPresent() && !existingByNumeric.get().getIsoNumeric().equals(oldCountry.getIsoNumeric())) {
            throw new DuplicatedCodeException(country.getIsoNumeric());
        }

        // Обновим сущность
        CountryDbEntity entity = new CountryDbEntity(country);
        entity.setId(findEntityIdByCode(code)); // Установим ID существующей записи
        countryRepository.save(entity);
    }

    @Override
    public void deleteByCode(String code) {
        CountryDbEntity entity = findEntityByCode(code);
        if (entity == null) {
            throw new CountryNotFoundException(code);
        }
        countryRepository.delete(entity);
    }

    // Вспомогательный метод для поиска сущности по коду
    private CountryDbEntity findEntityByCode(String code) {
        Optional<CountryDbEntity> entityOpt = Optional.empty();
        if (isNumeric(code)) {
            entityOpt = countryRepository.findByIsoNumeric(code);
        } else if (code.length() == 2) {
            entityOpt = countryRepository.findByIsoAlpha2(code);
        } else if (code.length() == 3) {
            entityOpt = countryRepository.findByIsoAlpha3(code);
        }
        return entityOpt.orElse(null);
    }

    // Вспомогательный метод для получения ID сущности по коду
    private Integer findEntityIdByCode(String code) {
        CountryDbEntity entity = findEntityByCode(code);
        return entity != null ? entity.getId() : null;
    }

    // Вспомогательный метод для проверки, является ли строка числом
    private boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        return str.matches("\\d+");
    }
}