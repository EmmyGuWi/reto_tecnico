package com.example.auth.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.auth.entity.UsuarioEntity;
import com.example.auth.repository.UsuarioRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner cargarUsuarioInicial(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {
            if (usuarioRepository.findByUsuario("admin").isEmpty()) {
                UsuarioEntity usuario = new UsuarioEntity(
                        "admin",
                        passwordEncoder.encode("12345678")
                );

                usuarioRepository.save(usuario);
            }
        };
    }
}
