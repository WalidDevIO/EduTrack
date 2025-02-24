package fr.elite.authapi.services;

import fr.elite.authapi.dtos.AuthResponse;
import fr.elite.authapi.dtos.AuthRequest;

public interface AuthService {
    AuthResponse register(AuthRequest request);

    AuthResponse login(AuthRequest request);

    AuthResponse unregister(Long id);

    AuthResponse resetPassword(Long id, String newPassword);

    boolean validateToken(String token);

    void logout(String token);
}
