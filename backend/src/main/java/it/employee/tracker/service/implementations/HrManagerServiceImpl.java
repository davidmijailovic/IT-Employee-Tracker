package it.employee.tracker.service.implementations;

import it.employee.tracker.model.HrManager;
import it.employee.tracker.model.SoftwareEngineer;
import it.employee.tracker.model.dto.UserDTO;
import it.employee.tracker.repository.HrManagerRepository;
import it.employee.tracker.service.interfaces.HrManagerService;
import it.employee.tracker.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HrManagerServiceImpl implements HrManagerService {
    @Autowired
    UserService userService;
    @Autowired
    HrManagerRepository hrManagerRepository;
    @Override
    public HrManager registerHrManager(UserDTO userDTO) {
        HrManager manager = new HrManager();
        manager = (HrManager) userService.registerUser(manager, userDTO);
        hrManagerRepository.save(manager);
        return manager;
    }
}
