package it.employee.tracker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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
}
