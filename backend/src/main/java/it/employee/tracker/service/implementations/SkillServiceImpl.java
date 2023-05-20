package it.employee.tracker.service.implementations;

import it.employee.tracker.model.Skill;
import it.employee.tracker.repository.SkillRepository;
import it.employee.tracker.service.interfaces.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository skillRepository;
    @Override
    public List<Skill> getSkills() {
        return skillRepository.findAll();
    }
    @Override
    public Skill findSkillByName(String name) {
        return skillRepository.findSkillByName(name);
    }
}
