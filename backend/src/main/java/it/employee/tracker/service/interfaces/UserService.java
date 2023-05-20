package it.employee.tracker.service.interfaces;

import it.employee.tracker.model.SoftwareEngineer;
import it.employee.tracker.model.User;
import it.employee.tracker.model.dto.ProjectDTO;
import it.employee.tracker.model.dto.SkillDTO;
import it.employee.tracker.model.dto.UserDTO;
import it.employee.tracker.model.response.UserResponse;

import java.util.List;
import java.util.NoSuchElementException;

public interface UserService {
    List<User> getAll();
    User findByEmail(String email);
    User findById(Long id) throws NoSuchElementException;
    User registerUser(User user, UserDTO userDTO);
    User editUser(User editUser, UserResponse user, Long id);
    List<SkillDTO> mapSkillToSkillDto(SoftwareEngineer softwareEngineer);
    List<ProjectDTO> mapProjectToProjectDto(SoftwareEngineer softwareEngineer);

}
