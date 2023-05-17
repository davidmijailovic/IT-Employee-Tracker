package it.employee.tracker.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import it.employee.tracker.model.Address;
import it.employee.tracker.model.dto.SkillDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String name;
    private String surname;
    private String email;
    @JsonProperty
    private Address address;
    private String phone;
    private String title;
    private String role;
    private List<SkillDTO> skills;
}
