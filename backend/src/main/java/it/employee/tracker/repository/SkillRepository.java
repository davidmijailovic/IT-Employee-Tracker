package it.employee.tracker.repository;

import it.employee.tracker.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

    Skill findSkillByName(String name);

}
