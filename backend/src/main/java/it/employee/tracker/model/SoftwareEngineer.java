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
    @Enumerated(EnumType.STRING)
    @Column
    private Seniority seniority;
    @Column
    private String verificationCode;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "software_engineer_id", referencedColumnName = "id")
    private List<SoftwareEngineerSkill> skills;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "software_engineer_id", referencedColumnName = "id")
    private List<SoftwareEngineerProject> projects;
}
