package com.jsp.lets_eat.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class User {

    private Integer id;
    private String name;
    private String email;
    private String password;
    private String role;
    @OneToOne
    private Menu menu;
    @OneToMany(mappedBy = "user")
    private List<Branch> branches;
}
