package com.devash.authapi.controller;

import com.devash.authapi.dto.AuthDTO;
import com.devash.authapi.dto.LoginDTO;
import com.devash.authapi.dto.RegisterDTO;
import com.devash.authapi.dto.UserProfileDTO;
import com.devash.authapi.entity.Employee;
import com.devash.authapi.security.JWTService;
import com.devash.authapi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private JWTService jwtService;

    @PostMapping("/login")
    public ResponseEntity<AuthDTO> login(@RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(authService.login(loginDTO));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthDTO> register(@RequestBody RegisterDTO registerDTO) {
        return ResponseEntity.ok(authService.register(registerDTO));
    }

    @GetMapping("/me")
    public ResponseEntity<UserProfileDTO> getCurrentUser(@RequestHeader("Authorization") String authHeader) {
        String token=authHeader.substring(7);
        String username=jwtService.extractUsername(token);
        return ResponseEntity.ok(authService.getCurrentUser(username));
    }

}
