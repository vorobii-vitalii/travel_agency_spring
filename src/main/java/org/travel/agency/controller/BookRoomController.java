package org.travel.agency.controller;

import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.travel.agency.dto.BookRoomRequest;
import org.travel.agency.entity.Room;
import org.travel.agency.security.CustomUserDetails;
import org.travel.agency.service.OrderService;
import org.travel.agency.service.RoomService;
import org.travel.agency.validation.BookRoomRequestValidator;

import java.time.LocalDate;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/book")
public class BookRoomController {
    private final RoomService roomService;
    private final OrderService orderService;
    private final BookRoomRequestValidator bookRoomRequestValidator;
    private final Environment env;

    @GetMapping("/{hotelId}")
    public String showBookForm( Model model,
                                @PathVariable Long hotelId,
                                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        if (start == null || end == null) {
            return "prompt_book_room_period";
        }
        if (start.isAfter(end)) {
            model.addAttribute("message", env.getRequiredProperty("errors.date.start_should_precede"));
            return "prompt_book_room_period";
        }
        List<Room> availableRooms = roomService.getAllByHotelIdAndDateBeyond(hotelId, start, end);
        if (availableRooms.isEmpty()) {
            model.addAttribute("message", env.getRequiredProperty("errors.rooms.result.empty"));
            return "prompt_book_room_period";
        }
        model.addAttribute("bookRoomRequest", new BookRoomRequest(start, end, null));
        model.addAttribute("availableRooms", availableRooms);
        return "book_room";
    }

    @PostMapping("/process")
    public String processBookRequest(Model model,
                                     @ModelAttribute("bookRoomRequest") BookRoomRequest bookRoomRequest,
                                     Errors errors) {
        bookRoomRequestValidator.validate(bookRoomRequest, errors);
        if (errors.hasErrors()) {
            model.addAttribute("message", env.getRequiredProperty("errors.room.not_available"));
            return "prompt_book_room_period";
        }
        orderService.addOrder(bookRoomRequest, getIdOfCurrentLoggedInUser());
        return "redirect:/";
    }

    private Long getIdOfCurrentLoggedInUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomUserDetails userDetails = (CustomUserDetails) principal;
        return userDetails.getUserId();
    }

}
