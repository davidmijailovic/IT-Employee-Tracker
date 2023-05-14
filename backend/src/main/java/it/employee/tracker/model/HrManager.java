package it.employee.tracker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class HrManager extends User
{
    @Column
    private String verificationCode;
}
