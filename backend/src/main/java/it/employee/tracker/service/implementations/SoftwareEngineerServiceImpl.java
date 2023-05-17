package it.employee.tracker.service.implementations;

import it.employee.tracker.model.SoftwareEngineer;
import it.employee.tracker.model.dto.UserDTO;
import it.employee.tracker.repository.SoftwareEngineerRepository;
import it.employee.tracker.service.interfaces.SoftwareEngineerService;
import it.employee.tracker.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SoftwareEngineerServiceImpl implements SoftwareEngineerService {
    @Autowired
    UserService userService;
    @Autowired
    SoftwareEngineerRepository softwareEngineerRepository;
    @Override
    public SoftwareEngineer registerEngineer(UserDTO userDTO) {
        SoftwareEngineer engineer = new SoftwareEngineer();
        engineer = (SoftwareEngineer) userService.registerUser(engineer, userDTO);
        softwareEngineerRepository.save(engineer);
        return engineer;
    }

    @Override
    public SoftwareEngineer findById(Long id) {
        return softwareEngineerRepository.findById(id).get();
    }
}
