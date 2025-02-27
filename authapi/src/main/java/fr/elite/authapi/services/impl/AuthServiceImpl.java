package fr.elite.authapi.services.impl;

import fr.elite.authapi.dtos.responses.BasicResponse;
import fr.elite.authapi.dtos.responses.MeResponse;
import fr.elite.authapi.dtos.responses.TokenResponse;
import fr.elite.authapi.dtos.requests.AuthRequest;
import fr.elite.authapi.dtos.requests.RegisterRequest;
import fr.elite.authapi.entities.Access;
import fr.elite.authapi.repositories.AccessRepository;
import fr.elite.authapi.security.JwtUtil;
import fr.elite.authapi.services.AuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("authService")
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AccessRepository accessRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;

    @Override
    public BasicResponse register(RegisterRequest request) {
        if (accessRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        Access access = new Access();
        access.setUsername(request.getUsername());
        access.setPassword(encoder.encode(request.getPassword()));
        access.setStudent(request.getStudent());

        accessRepository.save(access);
        return new BasicResponse("User registered successfully");
    }

    @Override
    public TokenResponse login(AuthRequest request) {
        Optional<Access> accessOpt = accessRepository.findByUsername(request.getUsername());
        if (accessOpt.isEmpty() || !encoder.matches(request.getPassword(), accessOpt.get().getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(request.getUsername());
        return new TokenResponse("Login successful", token);
    }

    @Override
    public BasicResponse unregister(String token) {
        String username = jwtUtil.getUsername(token);
        jwtUtil.invalidateToken(token);
        accessRepository.deleteByUsername(username);
        return new BasicResponse("User unregistered successfully");
    }

    @Override
    public BasicResponse resetPassword(String username, String newPassword) {
        Access access = accessRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        access.setPassword(encoder.encode(newPassword));
        accessRepository.save(access);
        return new BasicResponse("Password reset successful");
    }

    @Override
    public MeResponse validateToken(String token) {
        boolean valid = jwtUtil.validateToken(token);
        Access access = null;
        if(valid) {
            String username = jwtUtil.getUsername(token);
            access = accessRepository.findByUsername(username).orElse(null);
        }

        return new MeResponse(valid, access.getStudent());
    }

    @Override
    public void logout(String token) {
        jwtUtil.invalidateToken(token);
    }
}
