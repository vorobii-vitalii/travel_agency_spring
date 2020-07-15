package org.travel.agency.controller;

import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.travel.agency.entity.User;
import org.travel.agency.security.SecurityService;
import org.travel.agency.service.AuthService;
import org.travel.agency.validation.UserValidator;

@Controller
@AllArgsConstructor
@RequestMapping
public class AuthController {
    private final AuthService authService;
    private final SecurityService securityService;
    private final UserValidator userValidator;
    private final Environment env;

    @GetMapping("/login")
    public ModelAndView showLoginForm(String error, String logout) {
        ModelAndView modelAndView = new ModelAndView("login");
        if (error != null) {
            modelAndView.addObject("error", env.getProperty("error.credentials.incorrect"));
        }
        if (logout != null) {
            modelAndView.addObject("message", env.getProperty("message.logout.success"));
        }
        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView showRegistrationForm() {
        ModelAndView modelAndView = new ModelAndView("registration");
        modelAndView.addObject("userToRegister", new User());
        return modelAndView;
    }

    @PostMapping("/register")
    public String processRegistration(@ModelAttribute("userToRegister") User user, Errors errors) {
        userValidator.validate(user, errors);
        if (errors.hasErrors()) {
            return "registration";
        }
        String email = user.getEmail(), password = user.getPassword();
        authService.registerUser(user);
        securityService.autoLogin(email, password);
        return "redirect:/hotels";
    }

}
