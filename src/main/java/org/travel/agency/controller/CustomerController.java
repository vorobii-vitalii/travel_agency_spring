package org.travel.agency.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.travel.agency.constants.UserRoles;
import org.travel.agency.entity.User;
import org.travel.agency.service.OrderService;
import org.travel.agency.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerController {
    private final UserService userService;
    private final OrderService orderService;

    @GetMapping
    public ModelAndView getAllCustomers() {
        ModelAndView modelAndView = new ModelAndView("customers");
        List<User> customers = userService.getAllByRole(UserRoles.CUSTOMER.toString());
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("/{customerId}")
    public ModelAndView getCustomerDetails(@PathVariable Long customerId) {
        ModelAndView modelAndView = new ModelAndView("customer_details");
        User customer = userService.getById(customerId);
        modelAndView.addObject("customer", customer);
        modelAndView.addObject("orders", orderService.getAllByUserId(customerId));
        return modelAndView;
    }

}
