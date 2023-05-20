package it.employee.tracker.service.interfaces;

import it.employee.tracker.model.Project;
import it.employee.tracker.model.Skill;
import it.employee.tracker.model.SoftwareEngineer;
import it.employee.tracker.model.dto.UserDTO;

public interface SoftwareEngineerService {
    SoftwareEngineer registerEngineer(UserDTO userDTO);
    SoftwareEngineer findById(Long id);
    SoftwareEngineer addSkill(SoftwareEngineer engineer, Skill skill, String grade);
    void updateSkillValue(SoftwareEngineer softwareEngineer, Skill skill, String newValue);
    void updateProjectDescription(SoftwareEngineer softwareEngineer, Project project, String description);
}
