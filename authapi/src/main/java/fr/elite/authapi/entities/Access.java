package fr.elite.authapi.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Access {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 4, max = 50)
    @Column(unique = true)
    private String username;

    @NotBlank
    private String password; // Stocké haché avec BCrypt

    /**
     * Si null, l'accès correspond à un compte admin.
     */
    private Long student;
}
