package it.employee.tracker.service.interfaces;

import it.employee.tracker.model.HrManager;
import it.employee.tracker.model.dto.UserDTO;

public interface HrManagerService {
    HrManager registerHrManager(UserDTO userDTO);
}
