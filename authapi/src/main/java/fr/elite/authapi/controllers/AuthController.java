package fr.elite.authapi.controllers;

import fr.elite.authapi.dtos.requests.AuthRequest;
import fr.elite.authapi.dtos.requests.RegisterRequest;
import fr.elite.authapi.dtos.responses.BasicError;
import fr.elite.authapi.exceptions.BannedTokenException;
import fr.elite.authapi.services.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        try {
            return ResponseEntity.ok(authService.register(request));
        } catch(RuntimeException e) {
            return ResponseEntity.badRequest().body(new BasicError(e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequest request) {
        try {
            return ResponseEntity.ok(authService.login(request));
        } catch(RuntimeException e) {
            return ResponseEntity.badRequest().body(new BasicError(e.getMessage()));
        }
    }

    @DeleteMapping("/unregister")
    public ResponseEntity<?> unregister(@RequestHeader("Authorization") String authHeader) {
        try {
            String token = authHeader.replace("Bearer ", "");
            return ResponseEntity.ok(authService.unregister(token));
        } catch(RuntimeException e) {
            return ResponseEntity.badRequest().body(new BasicError(e.getMessage()));
        }
    }

    @PutMapping("/reset-password/{username}")
    public ResponseEntity<?> resetPassword(@PathVariable String username, @RequestBody String newPassword) {
        return ResponseEntity.ok(authService.resetPassword(username, newPassword));
    }

    @GetMapping("/me")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        try {
            return ResponseEntity.ok(authService.validateToken(token));
        } catch(BannedTokenException e) {
            return ResponseEntity.status(403).body(new BasicError(e.getMessage()));
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(new BasicError("Invalid token"));
        }
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        authService.logout(token);
        return ResponseEntity.noContent().build();
    }
}
