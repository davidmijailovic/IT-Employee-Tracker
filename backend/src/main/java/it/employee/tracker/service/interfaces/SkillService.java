package it.employee.tracker.service.interfaces;

import it.employee.tracker.model.Skill;
import java.util.List;
import java.util.Optional;

public interface SkillService {
    List<Skill> getSkills();
    Skill findSkillByName(String name);
}
