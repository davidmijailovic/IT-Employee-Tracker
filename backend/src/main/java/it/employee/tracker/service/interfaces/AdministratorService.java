package it.employee.tracker.service.interfaces;

import it.employee.tracker.model.Administrator;
import it.employee.tracker.model.dto.UserDTO;

public interface AdministratorService {
    Administrator registerAdministrator(UserDTO userDTO);
}
