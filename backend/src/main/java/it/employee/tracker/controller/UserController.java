package it.employee.tracker.controller;


import it.employee.tracker.model.User;
import it.employee.tracker.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("getAll")
    public List<User> getUsers() {
        return userService.getAll();
    }

    // other controller methods
}
