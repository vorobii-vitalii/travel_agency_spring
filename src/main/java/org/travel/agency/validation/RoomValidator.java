package org.travel.agency.validation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.travel.agency.entity.Room;
import org.travel.agency.service.RoomService;

import java.util.List;

@Component
@AllArgsConstructor
public class RoomValidator implements Validator {
    private final RoomService roomService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Room.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Room room = (Room) o;
        if (alreadyContainsInHotel(room.getRoomNumber(), room.getHotelId()))
            errors.rejectValue("roomNumber", "error.room.already_exists");
    }

    private boolean alreadyContainsInHotel(Long roomNumber, Long hotelId) {
        List<Room> roomsByGivenHotel = roomService.getAllByHotelId(hotelId);
        return roomsByGivenHotel
                .stream()
                .anyMatch(room -> room.getRoomNumber().equals(roomNumber));
    }

}
