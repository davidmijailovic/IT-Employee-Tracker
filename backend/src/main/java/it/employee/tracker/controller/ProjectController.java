package it.employee.tracker.controller;

import it.employee.tracker.model.Project;
import it.employee.tracker.model.Skill;
import it.employee.tracker.model.SoftwareEngineer;
import it.employee.tracker.model.request.ProjectRequest;
import it.employee.tracker.model.request.SkillRequest;
import it.employee.tracker.service.interfaces.ProjectService;
import it.employee.tracker.service.interfaces.SoftwareEngineerService;
import it.employee.tracker.util.Validations;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project")
@CrossOrigin(origins = "https://localhost:4200")
public class ProjectController {

    @Autowired
    private SoftwareEngineerService softwareEngineerService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private Validations validations;


    @PutMapping
    @PreAuthorize("hasAuthority('EDIT_PROJECT_PERMISSION')")
    public ResponseEntity<?> editProject(@RequestBody @Valid ProjectRequest projectRequest) {
        SoftwareEngineer engineer = this.softwareEngineerService.findById(projectRequest.getUserId());
        if (engineer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Project project = projectService.findProjectByName(projectRequest.getName());
        if(validations.hasProjectWithName(engineer, projectRequest.getName())){
            this.softwareEngineerService.updateProjectDescription(engineer, project, projectRequest.getDescription());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        }
}

