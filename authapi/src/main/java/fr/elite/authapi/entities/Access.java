package fr.elite.authapi.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Access {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String username;

    @NotBlank
    private String password; // Stocké haché avec BCrypt

    /**
     * Si null, l'accès correspond à un compte admin.
     */
    @Column(unique = true)
    private Long student;
}
