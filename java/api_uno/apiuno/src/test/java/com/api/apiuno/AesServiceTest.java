package com.api.apiuno;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.api.apiuno.Services.AesService;

@SpringBootTest
public class AesServiceTest {
        @Autowired
    private AesService aesService;

    @Test
    void pruebaAES() {

        String original = "jejdjw134&3#$$";

        String cifrado =
            aesService.cifrar(original);

        String descifrado =
            aesService.descifrar(cifrado);

        System.out.println("Original: " + original);
        System.out.println("Cifrado: " + cifrado);
        System.out.println("Descifrado: " + descifrado);

        assertEquals(
            original,
            descifrado
        );
    }
}
