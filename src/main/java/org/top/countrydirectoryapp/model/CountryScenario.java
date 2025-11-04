package org.top.countrydirectoryapp.model;

import java.util.List;

// CountryScenario - сценарии для работы со странами
public class CountryScenario {

    // хранилище стран
    private final CountryStorage storage;

    public CountryScenario(CountryStorage storage) {
        this.storage = storage;
    }

    // getAll - получение всех стран из справочника
    // вход: -
    // выход: список стран
    // исключения: -
    public List<Country> getAll() {
        throw new UnsupportedOperationException("implement me");
    }

    // get - получение страны по коду
    // вход: код страны в любом формате (isoAlpha2, isoAlpha3, isoNumeric)
    // выход: страна с заданным кодом
    // исключения:
    //  - CountryNotFoundException - страна с данным кодом не найдена
    //  - InvalidCodeException - переданный код не является валидным кодом страны
    public Country get(String code) {
        throw new UnsupportedOperationException("implement me");
    }

    // store - добавление новой страны в справочник
    // вход: объект страны для добавления с заполненными полями
    // выход: -
    // исключения:
    //  - InvalidCodeException - код переданной страны не является валидным кодом страны
    //  - DuplicatedCodeException - код переданной страны уже встречается в другой записи
    public void store(Country country) {
        throw new UnsupportedOperationException("implement me");
    }

    // edit - редактирование страны, разрешено редактировать все поля кроме кодов
    // вход:
    //  - code: код страны в любом формате (isoAlpha2, isoAlpha3, isoNumeric), по которому нужно найти запись
    //  - country: объект страны, в котором:
    //    - поля кодов (isoAlpha2, isoAlpha3, isoNumeric) должны совпадать с кодами в существующей записи
    //    - значения остальных полей могут отличаться от объекта в системе
    // выход: -
    // исключения:
    //  - CountryNotFoundException - страна с данным кодом не найдена
    //  - InvalidCodeException - переданный код не является валидным кодом страны
    public void edit(String code, Country country) {
        throw new UnsupportedOperationException("implement me");
    }

    // delete - удаление страны из справочника по коду
    // вход: код страны в любом формате (isoAlpha2, isoAlpha3, isoNumeric)
    // выход: -
    // исключения:
    //  - CountryNotFoundException - страна с данным кодом не найдена
    //  - InvalidCodeException - переданный код не является валидным кодом страны
    public void delete(String code) {
        throw new UnsupportedOperationException("implement me");
    }

    // validateCode - вспомогательный метод валидации кода (пока заглушка)
    // Принимает код, проверяет его формат, бросает InvalidCodeException если невалидный
    private void validateCode(String code) {
        throw new UnsupportedOperationException("implement me");
    }
}