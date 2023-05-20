package it.employee.tracker.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SkillRequest {

    @NotNull
    private Long userId;
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private String grade;

}
