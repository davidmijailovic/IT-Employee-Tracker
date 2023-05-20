package it.employee.tracker.service.interfaces;

import it.employee.tracker.model.Project;

public interface ProjectService {
    Project findProjectByName(String name);

}
