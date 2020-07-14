package org.travel.agency.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.travel.agency.entity.Country;
import org.travel.agency.entity.Hotel;
import org.travel.agency.service.CountryService;
import org.travel.agency.service.HotelService;
import org.travel.agency.validation.HotelValidator;

import java.util.List;


@Controller
@RequestMapping("/hotels")
@AllArgsConstructor
public class HotelController {
    private final HotelService hotelService;
    private final CountryService countryService;
    private final HotelValidator hotelValidator;

    @GetMapping
    public ModelAndView getAllHotels() {
        ModelAndView modelAndView = new ModelAndView("hotels");
        modelAndView.addObject("hotels", hotelService.getAll());
        return modelAndView;
    }

    @GetMapping("/new")
    public ModelAndView showAddForm() {
        ModelAndView  modelAndView = new ModelAndView("add_hotel");
        modelAndView.addObject("hotel", new Hotel());
        List<Country> countryList = countryService.getAll();
        System.out.println(countryList);
        modelAndView.addObject("countries", countryList);
        return modelAndView;
    }

    @PostMapping("/edit")
    public String editTheHotel(@ModelAttribute("hotel") Hotel hotel, Errors errors) {
        System.out.println(hotel);
        hotelValidator.validate(hotel, errors);
        if (errors.hasErrors()) {
            return "edit_hotel";
        }
        hotelService.save(hotel);
        return "redirect:/hotels";
    }

    @PostMapping("/add")
    public String addNewHotel(@ModelAttribute("hotel") Hotel hotel, Errors errors) {
        hotelValidator.validate(hotel, errors);
        if (errors.hasErrors()) {
            return "add_hotel";
        }
        hotelService.save(hotel);
        return "redirect:/hotels";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editHotelById(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("edit_hotel");
        modelAndView.addObject("hotel", hotelService.getById(id));
        modelAndView.addObject("countries", countryService.getAll());
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public String deleteHotelById(@PathVariable Long id) {
        hotelService.deleteById(id);
        return "redirect:/hotels";
    }

}
