package it.employee.tracker.service.interfaces;

import it.employee.tracker.model.User;
import it.employee.tracker.model.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User findByEmail(String email);
    User registerUser(User user, UserDTO userDTO);

}
