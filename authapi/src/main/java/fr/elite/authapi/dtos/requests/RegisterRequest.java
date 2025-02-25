package fr.elite.authapi.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "Le psuedo est obligatoire.")
    private String username;

    @NotBlank(message = "Le mot de passe est obligatoire.")
    private String password;

    @NotNull(message = "Le numéro étudiant est obligatoire.")
    private Long student;
}
