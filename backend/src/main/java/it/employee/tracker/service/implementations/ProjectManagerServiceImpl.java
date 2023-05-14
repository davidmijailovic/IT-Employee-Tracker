package it.employee.tracker.service.implementations;

import it.employee.tracker.model.HrManager;
import it.employee.tracker.model.ProjectManager;
import it.employee.tracker.model.dto.UserDTO;
import it.employee.tracker.repository.ProjectManagerRepository;
import it.employee.tracker.service.interfaces.ProjectManagerService;
import it.employee.tracker.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectManagerServiceImpl implements ProjectManagerService {
    @Autowired
    UserService userService;
    @Autowired
    ProjectManagerRepository projectManagerRepository;
    @Override
    public ProjectManager registerProjectManager(UserDTO userDTO) {
        ProjectManager manager = new ProjectManager();
        manager = (ProjectManager) userService.registerUser(manager, userDTO);
        projectManagerRepository.save(manager);
        return manager;
    }
}
