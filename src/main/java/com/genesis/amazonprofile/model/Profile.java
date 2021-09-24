package com.genesis.amazonprofile.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import java.util.UUID;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profile {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    @Email(message = "Invalid email address")
    private String email;
    private String imagePath;
    private String imageFileName;

    public Profile(String firstName, String lastName, String email) {
        id = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
