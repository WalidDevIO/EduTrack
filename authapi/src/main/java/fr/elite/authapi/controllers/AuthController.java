package fr.elite.authapi.controllers;

import fr.elite.authapi.dtos.AuthRequest;
import fr.elite.authapi.dtos.AuthResponse;
import fr.elite.authapi.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @DeleteMapping("/unregister/{id}")
    public ResponseEntity<AuthResponse> unregister(@PathVariable Long id) {
        return ResponseEntity.ok(authService.unregister(id));
    }

    @PutMapping("/reset-password/{id}")
    public ResponseEntity<AuthResponse> resetPassword(@PathVariable Long id, @RequestBody String newPassword) {
        return ResponseEntity.ok(authService.resetPassword(id, newPassword));
    }

    @GetMapping("/validate-token")
    public ResponseEntity<Boolean> validateToken(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        return ResponseEntity.ok(authService.validateToken(token));
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        authService.logout(token);
        return ResponseEntity.ok().build();
    }
}
