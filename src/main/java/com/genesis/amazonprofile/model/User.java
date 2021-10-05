package com.genesis.amazonprofile.model;

import com.genesis.amazonprofile.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    @Email(message = "Invalid email address")
    private String email;
    private String imagePath;
    private String imageFileName;
    @ElementCollection
    private Set<Roles> roles = new HashSet<Roles>();
    // TODO : encrypt password
    private String password;

}
