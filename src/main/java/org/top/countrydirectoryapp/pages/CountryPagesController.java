package org.top.countrydirectoryapp.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.top.countrydirectoryapp.model.*;

import java.util.List;

// CountryPagesController - контроллер для работы со странами через страницы
@Controller
@RequestMapping("/country") // Все URL будут начинаться с /country
public class CountryPagesController {

    private final CountryScenario countries;

    public CountryPagesController(CountryScenario countries) {
        this.countries = countries;
    }

    // GET /country - отображение списка всех стран
    @GetMapping
    public String getAll(Model model) {
        List<Country> allCountries = countries.getAll();
        model.addAttribute("countries", allCountries);
        return "country/list";
    }

    // GET /country/new - отображение формы для добавления новой страны
    @GetMapping("new")
    public String getNewCountry(Model model) {
        model.addAttribute("country", new Country("", "", "", "", "", 0L, 0L));
        return "country/add-form";
    }

    // POST /country/new - обработка отправки формы добавления
    @PostMapping("new")
    public String postNewCountry(Country country, RedirectAttributes ra) {
        try {
            countries.store(country);
            ra.addFlashAttribute("message", "Страна успешно добавлена");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMessage", e.getMessage());
            // Возвращаемся на форму с ошибкой
            return "redirect:/country/new";
        }
        return "redirect:/country"; // Редирект на список
    }

    // GET /country/edit/{code} - отображение формы редактирования
    @GetMapping("edit/{code}")
    public String getEditCountry(@PathVariable String code, Model model) {
        try {
            Country country = countries.get(code); 
            model.addAttribute("country", country); 
        } catch (Exception e) {
            model.addAttribute("country", new Country());
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "country/edit-form";
    }

    // POST /country/edit/{code} - обработка отправки формы редактирования
    @PostMapping("edit/{code}")
    public String postEditCountry(@PathVariable String code, Country country, RedirectAttributes ra) {
        country.setIsoAlpha2(countries.get(code).getIsoAlpha2()); 
        country.setIsoAlpha3(countries.get(code).getIsoAlpha3());
        country.setIsoNumeric(countries.get(code).getIsoNumeric());

        try {
            countries.edit(code, country);
            ra.addFlashAttribute("message", "Страна успешно отредактирована");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMessage", e.getMessage());
            // Возвращаемся на форму с ошибкой
            return "redirect:/country/edit/" + code;
        }
        return "redirect:/country"; // Редирект на список
    }

    @GetMapping("delete/{code}")
    public String deleteCountry(@PathVariable String code, RedirectAttributes ra) {
        try {
            countries.delete(code); // Вызываем метод сценария
            ra.addFlashAttribute("message", "Страна успешно удалена");
        } catch (Exception e) {
            // Обработка ошибок (например, CountryNotFoundException, InvalidCodeException)
            ra.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/country";
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/country";
    }



}