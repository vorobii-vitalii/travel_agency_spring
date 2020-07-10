package org.travel.agency.exception_handlers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.travel.agency.exceptions.NotFoundException;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    public String handleNotFoundException() {
        return "not_found";
    }

}
