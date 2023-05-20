package it.employee.tracker.service.implementations;

import it.employee.tracker.model.*;
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

    @Override
    public SoftwareEngineer addSkill(SoftwareEngineer engineer, Skill skill, String grade) {
        SoftwareEngineerSkill newSkill = new SoftwareEngineerSkill();
        newSkill.setSkill(skill);
        newSkill.setValue(grade);
        engineer.getSkills().add(newSkill);
        return softwareEngineerRepository.save(engineer);
    }

    @Override
    public void updateSkillValue(SoftwareEngineer softwareEngineer, Skill skill, String newValue) {
        for (SoftwareEngineerSkill softwareEngineerSkill : softwareEngineer.getSkills()) {
            if (softwareEngineerSkill.getSkill().equals(skill)) {
                softwareEngineerSkill.setValue(newValue);
                softwareEngineerRepository.save(softwareEngineer);
                break;
            }
        }
    }

    @Override
    public void updateProjectDescription(SoftwareEngineer softwareEngineer, Project project, String description) {
        for (SoftwareEngineerProject softwareEngineerProject : softwareEngineer.getProjects()) {
            if (softwareEngineerProject.getProject().equals(project)) {
                softwareEngineerProject.setDescription(description);
                softwareEngineerRepository.save(softwareEngineer);
                break;
            }
        }
    }

}
