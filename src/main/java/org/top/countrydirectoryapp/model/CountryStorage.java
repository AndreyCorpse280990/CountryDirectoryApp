package org.top.countrydirectoryapp.model;

import java.util.List;

public interface CountryStorage {
    List<Country> selectAll();
    Country selectByCode(String code); // Возвращает null, если не найдено
    void insert(Country country);
    void update(String code, Country country);
    void deleteByCode(String code);
}