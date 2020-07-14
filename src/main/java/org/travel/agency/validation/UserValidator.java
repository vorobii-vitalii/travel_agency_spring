package org.travel.agency.validation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.travel.agency.constants.UserConstrains;
import org.travel.agency.entity.User;
import org.travel.agency.exceptions.NotFoundException;
import org.travel.agency.service.UserService;

@Component
@AllArgsConstructor
public class UserValidator implements Validator {
    private final UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        if (user.getPassword().length() < UserConstrains.PASSWORD_MIN_LENGTH) {
            errors.rejectValue("password", "error.user_password.too_short");
        }
        if (emailAlreadyTaken(user.getEmail())) {
            errors.rejectValue("email", "error.user_email.already_taken");
        }
    }

    private boolean emailAlreadyTaken(String email) {
        try {
            userService.getByEmail(email);
            return true;
        }
        catch (NotFoundException ignored) {}
        return false;
    }

}
