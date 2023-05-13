package it.employee.tracker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import com.fasterxml.jackson.annotation.JsonIgnore;


// POJO koji implementira Spring Security GrantedAuthority kojim se mogu definisati role u aplikaciji
@Entity
@Table(name="role")
@Getter
@Setter
public class Role implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="name")
    private String name;


    @JsonIgnore
    @Override
    public String getAuthority() {
        return name;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }


}