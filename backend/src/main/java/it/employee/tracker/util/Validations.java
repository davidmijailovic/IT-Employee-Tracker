package it.employee.tracker.util;

import it.employee.tracker.model.SoftwareEngineer;
import it.employee.tracker.model.SoftwareEngineerProject;
import it.employee.tracker.model.SoftwareEngineerSkill;
import org.springframework.stereotype.Component;

@Component
public class Validations {
    public boolean hasSkillWithName(SoftwareEngineer softwareEngineer, String skillName) {
        for (SoftwareEngineerSkill softwareEngineerSkill : softwareEngineer.getSkills()) {
            if (softwareEngineerSkill.getSkill().getName().equalsIgnoreCase(skillName)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasProjectWithName(SoftwareEngineer softwareEngineer, String projectName) {
        for (SoftwareEngineerProject softwareEngineerProject : softwareEngineer.getProjects()) {
            if (softwareEngineerProject.getProject().getName().equalsIgnoreCase(projectName)) {
                return true;
            }
        }
        return false;
    }
}
