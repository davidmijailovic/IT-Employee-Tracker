package it.employee.tracker.service.implementations;

import it.employee.tracker.model.Administrator;
import it.employee.tracker.model.dto.UserDTO;
import it.employee.tracker.repository.AdministratorRepostiory;
import it.employee.tracker.service.interfaces.AdministratorService;
import it.employee.tracker.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministratorServiceImpl implements AdministratorService {
    @Autowired
    UserService userService;
    @Autowired
    AdministratorRepostiory administratorRepostiory;
    @Override
    public Administrator registerAdministrator(UserDTO userDTO) {
        Administrator administrator = new Administrator();
        administrator = (Administrator) userService.registerUser(administrator, userDTO);
        administrator.setEnabled(true);
        administrator.setApproved(true);
        administratorRepostiory.save(administrator);
        return administrator;
    }
}
