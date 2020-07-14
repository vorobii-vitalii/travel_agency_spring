package org.travel.agency.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.travel.agency.dto.BookRoomRequest;
import org.travel.agency.entity.Order;
import org.travel.agency.service.RoomService;

@Component
@AllArgsConstructor
public class BookRoomOrderMapper implements Mapper<BookRoomRequest, Order> {
    private final RoomService roomService;

    @Override
    public BookRoomRequest from(Order order) {
        BookRoomRequest bookRoomRequest = new BookRoomRequest();
        bookRoomRequest.setStart(order.getDateFrom());
        bookRoomRequest.setEnd(order.getDateTill());
        bookRoomRequest.setRoomId(order.getRoom().getId());
        return bookRoomRequest;
    }

    @Override
    public Order to(BookRoomRequest bookRoomRequest) {
        Order order = new Order();
        order.setDateFrom(bookRoomRequest.getStart());
        order.setDateTill(bookRoomRequest.getEnd());
        order.setRoom(roomService.getById(bookRoomRequest.getRoomId()));
        return order;
    }
}
