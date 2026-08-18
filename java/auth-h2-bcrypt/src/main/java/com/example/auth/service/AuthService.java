package com.example.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.auth.repository.UsuarioRepository;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean autenticar(String usuario, String passwordPlano) {
        return usuarioRepository.findByUsuario(usuario)
                .map(usuarioBD ->
                        passwordEncoder.matches(
                                passwordPlano,
                                usuarioBD.getPassword()
                        )
                )
                .orElse(false);
    }
}
