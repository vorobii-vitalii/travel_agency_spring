package org.travel.agency.validation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.travel.agency.constants.HotelConstrains;
import org.travel.agency.entity.Hotel;
import org.travel.agency.service.CountryService;

@Component
@AllArgsConstructor
public class HotelValidator implements Validator {
    private final CountryService countryService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Hotel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Hotel hotel = (Hotel) o;
        String hotelCountry = hotel.getCountry().trim();
        String hotelName = hotel.getName().trim();
        if (hotelName.length() < HotelConstrains.HOTEL_NAME_MIN_LENGTH)
            errors.rejectValue("name", "error.hotel_name.too_short");
        if (!countryService.existsByName(hotelCountry))
            errors.rejectValue("country", "error.country.not_found");
    }
}
