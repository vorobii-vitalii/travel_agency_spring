package org.travel.agency.validation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.travel.agency.dto.BookRoomRequest;
import org.travel.agency.service.RoomService;

import java.time.LocalDate;

@Component
@AllArgsConstructor
public class BookRoomRequestValidator implements Validator {
    private final RoomService roomService;

    @Override
    public boolean supports(Class<?> aClass) {
        return BookRoomRequest.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        BookRoomRequest bookRoomRequest = (BookRoomRequest) o;
        Long roomId = bookRoomRequest.getRoomId();
        LocalDate start = bookRoomRequest.getStart();
        LocalDate end = bookRoomRequest.getEnd();
        if (roomService.havingOrdersDuringDateBetween(roomId, start, end)) {
            errors.rejectValue("roomId", "errors.room.room_is_busy");
        }
    }
}
