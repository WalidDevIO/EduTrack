package fr.elite.authapi.services.impl;

import fr.elite.authapi.dtos.AuthResponse;
import fr.elite.authapi.dtos.AuthRequest;
import fr.elite.authapi.entities.Access;
import fr.elite.authapi.repositories.AccessRepository;
import fr.elite.authapi.security.JwtUtil;
import fr.elite.authapi.services.AuthService;
import jakarta.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {
    private final AccessRepository accessRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder encoder;

    public AuthServiceImpl(AccessRepository accessRepository, JwtUtil jwtUtil) {
        this.accessRepository = accessRepository;
        this.jwtUtil = jwtUtil;
        this.encoder = new BCryptPasswordEncoder();
    }

    @Override
    public AuthResponse register(AuthRequest request) {
        if (accessRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        Access access = new Access();
        access.setUsername(request.getUsername());
        access.setPassword(encoder.encode(request.getPassword()));
        access.setStudent(null); // Par défaut, compte admin

        accessRepository.save(access);
        return new AuthResponse("User registered successfully");
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        Optional<Access> accessOpt = accessRepository.findByUsername(request.getUsername());
        if (accessOpt.isEmpty() || !encoder.matches(request.getPassword(), accessOpt.get().getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(request.getUsername());
        return new AuthResponse("Login successful", token);
    }

    @Override
    public AuthResponse unregister(String username) {
        accessRepository.deleteByUsername(username);
        return new AuthResponse("User unregistered successfully");
    }

    @Override
    public AuthResponse resetPassword(String username, String newPassword) {
        Access access = accessRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        access.setPassword(encoder.encode(newPassword));
        accessRepository.save(access);
        return new AuthResponse("Password reset successful");
    }

    @Override
    public boolean validateToken(String token) {
        return jwtUtil.validateToken(token);
    }

    @Override
    public void logout(String token) {
        jwtUtil.invalidateToken(token);
    }
}
