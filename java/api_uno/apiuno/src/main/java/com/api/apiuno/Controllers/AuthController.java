package com.api.apiuno.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apiuno.Pojo.LoginRequest;
import com.api.apiuno.Pojo.LoginResponse;
import com.api.apiuno.Services.JwtService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class AuthController {
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest request) {

        if (!"admin".equals(request.getUsername())
                || !"1234".equals(request.getPassword())) {

            return ResponseEntity
                    .status(401)
                    .body("Usuario o contraseña incorrectos");
        }

        String token =
                jwtService.generarToken(request.getUsername());

        return ResponseEntity.ok(
                new LoginResponse(token)
        );
    }
}
