package it.employee.tracker.service.interfaces;

import it.employee.tracker.model.SoftwareEngineer;
import it.employee.tracker.model.dto.UserDTO;

public interface SoftwareEngineerService {
    SoftwareEngineer registerEngineer(UserDTO userDTO);
    SoftwareEngineer findById(Long id);
}
