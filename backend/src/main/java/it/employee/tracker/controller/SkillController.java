package it.employee.tracker.controller;

import it.employee.tracker.model.Skill;
import it.employee.tracker.model.SoftwareEngineer;
import it.employee.tracker.model.SoftwareEngineerSkill;
import it.employee.tracker.model.request.SkillRequest;
import it.employee.tracker.service.interfaces.SkillService;
import it.employee.tracker.service.interfaces.SoftwareEngineerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skill")
@CrossOrigin(origins = "https://localhost:4200")
public class SkillController {
    @Autowired
    private SkillService skillService;
    @Autowired
    private SoftwareEngineerService softwareEngineer;


    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('SOFTWARE_ENGINEER')")
    public ResponseEntity<?> addSkill(@RequestBody @Valid SkillRequest skillReq) {
        SoftwareEngineer engineer = this.softwareEngineer.findById(skillReq.getUserId());
        if (engineer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Skill skill = skillService.findSkillByName(skillReq.getName());

        if(hasSkillWithName(engineer, skillReq.getName())){
            this.softwareEngineer.updateSkillValue(engineer, skill, skillReq.getGrade());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            SoftwareEngineer added = this.softwareEngineer.addSkill(engineer, skill, skillReq.getGrade());
            if (added == null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('SOFTWARE_ENGINEER')")
    public ResponseEntity<List<Skill>> getSkills(){
        List<Skill> skills = skillService.getSkills();
        return new ResponseEntity<>(skills, HttpStatus.OK);
    }

    private boolean hasSkillWithName(SoftwareEngineer softwareEngineer, String skillName) {
        for (SoftwareEngineerSkill softwareEngineerSkill : softwareEngineer.getSkills()) {
            if (softwareEngineerSkill.getSkill().getName().equalsIgnoreCase(skillName)) {
                return true;
            }
        }
        return false;
    }

}
