package org.travel.agency.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.travel.agency.entity.Hotel;
import org.travel.agency.service.HotelService;


@Controller
@RequestMapping("/hotels")
@AllArgsConstructor
public class HotelController {
    private final HotelService hotelService;

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
        return modelAndView;
    }

    @PostMapping("/save")
    public String saveHotel(@ModelAttribute("hotel") Hotel hotel) {
        hotelService.save(hotel);
        return "redirect:/hotels";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editHotelById(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("edit_hotel");
        modelAndView.addObject("hotel", hotelService.getById(id));
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public String deleteHotelById(@PathVariable Long id) {
        hotelService.deleteById(id);
        return "redirect:/hotels";
    }

}
