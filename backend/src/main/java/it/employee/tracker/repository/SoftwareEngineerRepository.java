package it.employee.tracker.repository;

import it.employee.tracker.model.SoftwareEngineer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoftwareEngineerRepository extends JpaRepository<SoftwareEngineer, Long> {
}
