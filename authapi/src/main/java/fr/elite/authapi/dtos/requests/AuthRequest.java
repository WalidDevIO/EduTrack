package fr.elite.authapi.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequest {
    @NotBlank(message="Le psuedo est obligatoire")
    private String username;

    @NotBlank(message="Le mot de passe est obligatoire")
    private String password;
}
