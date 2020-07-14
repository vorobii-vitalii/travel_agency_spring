package org.travel.agency.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.travel.agency.entity.Room;
import org.travel.agency.service.RoomService;
import org.travel.agency.validation.RoomValidator;

@Controller
@AllArgsConstructor
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;
    private final RoomValidator roomValidator;

    @GetMapping("/{hotelId}")
    public ModelAndView getRoomsByHotelId(@PathVariable Long hotelId) {
        ModelAndView modelAndView = new ModelAndView("rooms");
        Room room = new Room();
        room.setHotelId(hotelId);
        modelAndView.addObject("rooms", roomService.getAllByHotelId(hotelId));
        modelAndView.addObject("roomToAdd", room);
        return modelAndView;
    }

    @GetMapping("/delete/{roomId}")
    public String deleteRoomById(@PathVariable Long roomId) {
        Room deletedRoom = roomService.deleteById(roomId);
        return redirectToHotelRooms(deletedRoom.getHotelId());
    }

    @PostMapping
    public String addRoom(@ModelAttribute("roomToAdd") Room room, Errors errors) {
        roomValidator.validate(room, errors);
        if (!errors.hasErrors()) {
            roomService.save(room);
        }
        return redirectToHotelRooms(room.getHotelId());
    }

    private String redirectToHotelRooms(Long hotelId) {
        return "redirect:/rooms/" + hotelId;
    }

}
