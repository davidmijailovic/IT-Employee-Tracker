package it.employee.tracker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "software_engineer_project")
public class SoftwareEngineerProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
    @Column
    private String description;
    @Column
    private String duration;


}
