package it.employee.tracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SoftwareEngineer extends User {
    @Column
    private LocalDate employmentDate;
    @Column
    private Seniority seniority;
    @Column
    private String verificationCode;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "software_engineer_skill",
            joinColumns = @JoinColumn(name = "software_engineer_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private List<Skill> skills;

}
