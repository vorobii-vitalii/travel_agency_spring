package org.travel.agency.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.travel.agency.entity.Hotel;
import org.travel.agency.service.CountryService;
import org.travel.agency.service.HotelService;

import java.util.List;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class HomeController {
    private final HotelService hotelService;
    private final CountryService countryService;

    @GetMapping
    public ModelAndView showAllHotels( @RequestParam(value = "country", required = false) String country) {
        ModelAndView modelAndView = new ModelAndView("home_page");
        modelAndView.addObject("hotels", findHotelsByCountry(country));
        modelAndView.addObject("countries", countryService.getAll());
        return modelAndView;
    }

    private List<Hotel> findHotelsByCountry(String country) {
        if (country == null)
            return hotelService.getAll();
        return hotelService.getAllByCountry(country);
    }

}
