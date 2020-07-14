package org.travel.agency.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.travel.agency.constants.UserRoles;
import org.travel.agency.entity.Role;
import org.travel.agency.entity.User;
import org.travel.agency.service.AuthService;
import org.travel.agency.service.UserService;

import java.util.Set;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AuthService authService;
    private final UserService userService;

    @GetMapping
    public ModelAndView showAdminPage() {
        ModelAndView modelAndView = new ModelAndView("admin_page");
        modelAndView.addObject("users", userService.getAll());
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView showUserInfo(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("user_details");
        User user = userService.getById(id);
        modelAndView.addObject("isManager", isUserManager(user));
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping("/{id}")
    public String toggleManagerStatus(@PathVariable Long id) {
        System.out.println("Got request with id " + id);
        User user = userService.getById(id);
        authService.toggleRoleOfUser(UserRoles.MANAGER, user);
        return "redirect:/admin/" + id;
    }

    private boolean isUserManager(User user) {
        Set<Role> roles = user.getRoles();
        return roles.stream().anyMatch(role -> role.getName().equals("MANAGER"));
    }

}
