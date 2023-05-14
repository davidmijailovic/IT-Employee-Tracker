package it.employee.tracker.service.interfaces;

import it.employee.tracker.model.Role;

import java.util.List;

public interface RoleService {
    Role findById(Long id);
    List<Role> findByName(String name);
}
