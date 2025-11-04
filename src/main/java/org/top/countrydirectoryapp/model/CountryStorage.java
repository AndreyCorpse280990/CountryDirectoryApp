package org.top.countrydirectoryapp.model;

import java.util.List;
import java.util.Optional;

// CountryStorage - интерфейс хранилища стран
public interface CountryStorage {

    // selectAll - получение всех стран из хранилища
    List<Country> selectAll();

    // selectByCode - получение страны по любому коду (isoAlpha2, isoAlpha3, isoNumeric)
    // Возвращает Optional, потому что страна может не найтись
    Optional<Country> selectByCode(String code);

    // insert - вставка новой страны
    void insert(Country country);

    // deleteByCode - удаление записи по любому коду (isoAlpha2, isoAlpha3, isoNumeric)
    void deleteByCode(String code);

    // update - обновление страны (по коду в объекте country)
    void update(Country country);

    // selectByIsoAlpha2 - вспомогательный метод для поиска по конкретному коду
    Optional<Country> selectByIsoAlpha2(String code);

    // selectByIsoAlpha3 - вспомогательный метод для поиска по конкретному коду
    Optional<Country> selectByIsoAlpha3(String code);

    // selectByIsoNumeric - вспомогательный метод для поиска по конкретному коду
    Optional<Country> selectByIsoNumeric(String code);
}