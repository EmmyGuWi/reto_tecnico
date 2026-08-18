package com.example.auth;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.auth.service.AuthService;

@SpringBootTest
class AuthApplicationTests {

    @Autowired
    private AuthService authService;

    @Test
    void debeAutenticarUsuarioCorrecto() {
        assertThat(authService.autenticar("admin", "12345678")).isTrue();
    }

    @Test
    void debeRechazarPasswordIncorrecto() {
        assertThat(authService.autenticar("admin", "incorrecto")).isFalse();
    }

    @Test
    void debeRechazarUsuarioInexistente() {
        assertThat(authService.autenticar("nadie", "12345678")).isFalse();
    }
}
