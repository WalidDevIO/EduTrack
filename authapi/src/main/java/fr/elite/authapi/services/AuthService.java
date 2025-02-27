package fr.elite.authapi.services;

import fr.elite.authapi.dtos.responses.BasicResponse;
import fr.elite.authapi.dtos.responses.MeResponse;
import fr.elite.authapi.dtos.responses.TokenResponse;
import fr.elite.authapi.dtos.requests.AuthRequest;
import fr.elite.authapi.dtos.requests.RegisterRequest;

public interface AuthService {
    BasicResponse register(RegisterRequest request);

    TokenResponse login(AuthRequest request);

    BasicResponse unregister(String token);

    BasicResponse resetPassword(String username, String newPassword);

    MeResponse validateToken(String token);

    void logout(String token);
}
