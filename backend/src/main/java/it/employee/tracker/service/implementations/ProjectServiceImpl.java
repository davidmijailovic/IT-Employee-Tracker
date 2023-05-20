package it.employee.tracker.service.implementations;

import it.employee.tracker.model.Project;
import it.employee.tracker.repository.ProjectRepository;
import it.employee.tracker.service.interfaces.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Override
    public Project findProjectByName(String name) {
        return projectRepository.findByName(name);
    }
}
