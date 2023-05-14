package it.employee.tracker.service.interfaces;

import it.employee.tracker.model.ProjectManager;
import it.employee.tracker.model.dto.UserDTO;

public interface ProjectManagerService {
    ProjectManager registerProjectManager(UserDTO userDTO);
}
