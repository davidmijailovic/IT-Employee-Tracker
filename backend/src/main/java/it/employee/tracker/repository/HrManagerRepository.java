package it.employee.tracker.repository;

import it.employee.tracker.model.HrManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HrManagerRepository extends JpaRepository<HrManager, Long> {
}
