package it.employee.tracker.repository;

import it.employee.tracker.model.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratorRepostiory extends JpaRepository<Administrator, Long> {
}
