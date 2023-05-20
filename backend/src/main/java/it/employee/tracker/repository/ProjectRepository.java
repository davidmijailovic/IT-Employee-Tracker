package it.employee.tracker.repository;

import it.employee.tracker.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findByName(String name);
}
