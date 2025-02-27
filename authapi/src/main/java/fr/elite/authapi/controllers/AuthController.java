package fr.elite.authapi.controllers;

import fr.elite.authapi.dtos.requests.AuthRequest;
import fr.elite.authapi.dtos.requests.RegisterRequest;
import fr.elite.authapi.dtos.responses.BasicResponse;
import fr.elite.authapi.dtos.responses.MeResponse;
import fr.elite.authapi.dtos.responses.TokenResponse;
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
    public ResponseEntity<BasicResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @DeleteMapping("/unregister/{username}")
    public ResponseEntity<BasicResponse> unregister(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        return ResponseEntity.ok(authService.unregister(token));
    }

    @PutMapping("/reset-password/{username}")
    public ResponseEntity<BasicResponse> resetPassword(@PathVariable String username, @RequestBody String newPassword) {
        return ResponseEntity.ok(authService.resetPassword(username, newPassword));
    }

    @GetMapping("/me")
    public ResponseEntity<MeResponse> validateToken(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        return ResponseEntity.ok(authService.validateToken(token));
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        authService.logout(token);
        return ResponseEntity.noContent().build();
    }
}
