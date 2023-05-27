package it.employee.tracker.controller;

import it.employee.tracker.model.User;
import it.employee.tracker.model.dto.UserTokenState;
import it.employee.tracker.model.dto.JwtAuthenticationRequest;
import it.employee.tracker.model.dto.UserDTO;
import it.employee.tracker.service.interfaces.HrManagerService;
import it.employee.tracker.service.interfaces.ProjectManagerService;
import it.employee.tracker.service.interfaces.SoftwareEngineerService;
import it.employee.tracker.service.interfaces.UserService;
import it.employee.tracker.util.TokenUtils;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "https://localhost:4200")
public class AuthenticationController {

    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private SoftwareEngineerService softwareEngineerService;
    @Autowired
    private HrManagerService hrManagerService;
    @Autowired
    private ProjectManagerService projectManagerService;



    @PostMapping("/login")
    public ResponseEntity<UserTokenState> createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response) {

        User logUser = userService.findByEmail(authenticationRequest.getUsername());

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(), authenticationRequest.getPassword().concat(logUser.getSalt())));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();
        String access = tokenUtils.generateAccessToken(user);
        String refresh = tokenUtils.generateRefreshToken(user);
        int accessExpiresIn = tokenUtils.getAccessTokenExpiresIn();
        int refreshExpiresIn = tokenUtils.getRefreshTokenExpiresIn();

        UserTokenState userTokenState = new UserTokenState();
        userTokenState.setAccessToken(access);
        userTokenState.setRefreshToken(refresh);
        userTokenState.setAccessExpiresIn(accessExpiresIn);
        userTokenState.setRefreshExpiresIn(refreshExpiresIn);

        return ResponseEntity.ok(userTokenState);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> addUser(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult)  {

        if (this.userService.findByEmail(userDTO.getEmail()) != null) {
            return new ResponseEntity<>("A user with that email already exists!",HttpStatus.CONFLICT);
        } else if (!userDTO.getPassword().equals(userDTO.getRe_password())) {
            return new ResponseEntity<>("Passwords do not match!",HttpStatus.CONFLICT);
        }

        ResponseEntity<List<String>> errorMessages = ValidateRequest(bindingResult);
        if (errorMessages != null) return errorMessages;

        User registeredUser = null;
        String errorMessage = "";

        switch (userDTO.getAccountType()) {
            case SOFTWARE_ENGINEER:
                registeredUser = this.softwareEngineerService.registerEngineer(userDTO);
                errorMessage = "Failed to register software engineer.";
                break;
            case HR_MANAGER:
                registeredUser = this.hrManagerService.registerHrManager(userDTO);
                errorMessage = "Failed to register HR manager.";
                break;
            case PROJECT_MANAGER:
                registeredUser = this.projectManagerService.registerProjectManager(userDTO);
                errorMessage = "Failed to register project manager.";
                break;
        }

        if (registeredUser == null) {
            return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    public static ResponseEntity<List<String>> ValidateRequest(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            List<String> errorMessages = new ArrayList<>();
            for (ObjectError error : errors) {
                errorMessages.add(error.getDefaultMessage());
            }
            return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
        }
        return null;
    }


}
