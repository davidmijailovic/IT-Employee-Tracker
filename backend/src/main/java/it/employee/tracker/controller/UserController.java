package it.employee.tracker.controller;


import it.employee.tracker.model.SoftwareEngineer;
import it.employee.tracker.model.User;
import it.employee.tracker.model.dto.ProjectDTO;
import it.employee.tracker.model.dto.SkillDTO;
import it.employee.tracker.model.dto.UserDTO;
import it.employee.tracker.model.response.UserResponse;
import it.employee.tracker.service.interfaces.AdministratorService;
import it.employee.tracker.service.interfaces.SoftwareEngineerService;
import it.employee.tracker.service.interfaces.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static it.employee.tracker.controller.AuthenticationController.ValidateRequest;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private SoftwareEngineerService softwareEngineerService;

    @GetMapping("getAll")
    public List<User> getUsers() {
        return userService.getAll();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('SOFTWARE_ENGINEER', 'ADMINISTRATOR', 'ROLE_HR_MANAGER', 'ROLE_PROJECT_MANAGER')")
    public ResponseEntity<?> editUser(@PathVariable long id, @RequestBody UserResponse editInfo) {
        User forEdit = userService.findById(id);
        if (forEdit == null) {
            return new ResponseEntity<String>("User not found!", HttpStatus.NOT_FOUND);
        }

        try {
            User editedUser = userService.editUser(forEdit, editInfo, id);
            return new ResponseEntity<>(editedUser, HttpStatus.CREATED);
        } catch (AccessDeniedException ex) {
            String errorMessage = "Access denied: " + ex.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.FORBIDDEN);
        }
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('SOFTWARE_ENGINEER', 'ADMINISTRATOR', 'ROLE_HR_MANAGER', 'ROLE_PROJECT_MANAGER')")
    public ResponseEntity<UserResponse> findById(@PathVariable("id") Long id){
        try {
            User user = userService.findById(id);
            List<SkillDTO> skills = new ArrayList<>();
            List<ProjectDTO> projects = new ArrayList<>();
            if(user.getRoles().get(0).getName().equals("ROLE_SOFTWARE_ENGINEER")) {
                SoftwareEngineer softwareEngineer = softwareEngineerService.findById(id);
                skills = userService.mapSkillToSkillDto(softwareEngineer);
                projects = userService.mapProjectToProjectDto(softwareEngineer);
            }
            UserResponse responseUser = new UserResponse(
                    user.getName(),
                    user.getSurname(),
                    user.getEmail(),
                    user.getAddress(),
                    user.getPhone(),
                    user.getTitle(),
                    user.getRoles().get(0).getName().toString().substring(5),
                    skills,
                    projects
            );
            return new ResponseEntity<UserResponse>(responseUser, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
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
