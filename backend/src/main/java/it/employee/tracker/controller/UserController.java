package it.employee.tracker.controller;


import it.employee.tracker.model.User;
import it.employee.tracker.model.dto.UserDTO;
import it.employee.tracker.service.interfaces.AdministratorService;
import it.employee.tracker.service.interfaces.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static it.employee.tracker.controller.AuthenticationController.ValidateRequest;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AdministratorService administratorService;

    @GetMapping("getAll")
    public List<User> getUsers() {
        return userService.getAll();
    }

    @PostMapping("/admin")
    public ResponseEntity<?> addUser(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult, UriComponentsBuilder ucBuilder) throws UnsupportedEncodingException {

        if (this.userService.findByEmail(userDTO.getEmail()) != null) {
            return new ResponseEntity<>("A user with that email already exists!", HttpStatus.CONFLICT);
        } else if (!userDTO.getPassword().equals(userDTO.getRe_password())) {
            return new ResponseEntity<>("Passwords do not match!",HttpStatus.CONFLICT);
        }

        ResponseEntity<List<String>> errorMessages = ValidateRequest(bindingResult);
        if (errorMessages != null) return errorMessages;

        User registeredUser = this.administratorService.registerAdministrator(userDTO);
        String errorMessage = "Failed to register admin.";

        if (registeredUser == null) {
            return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);

    }

}
