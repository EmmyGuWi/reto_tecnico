package com.api.apiuno.Services;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Service
public class AesService {


private static final int GCM_TAG_LENGTH = 128;
    private static final int IV_LENGTH = 12;

    private final SecretKey secretKey;

    public AesService(
            @Value("${security.aes.key}") String aesKey) {

        if (aesKey.getBytes(StandardCharsets.UTF_8).length != 32) {
            throw new IllegalArgumentException(
                "La clave AES debe tener exactamente 32 bytes"
            );
        }

        this.secretKey = new SecretKeySpec(
            aesKey.getBytes(StandardCharsets.UTF_8),
            "AES"
        );
    }

    public String descifrar(String valorCifrado) {

        try {

            byte[] datos =
                Base64.getDecoder().decode(valorCifrado);

            byte[] iv = new byte[IV_LENGTH];

            System.arraycopy(
                datos,
                0,
                iv,
                0,
                IV_LENGTH
            );

            byte[] cipherText =
                new byte[datos.length - IV_LENGTH];

            System.arraycopy(
                datos,
                IV_LENGTH,
                cipherText,
                0,
                cipherText.length
            );

            Cipher cipher =
                Cipher.getInstance("AES/GCM/NoPadding");

            GCMParameterSpec spec =
                new GCMParameterSpec(
                    GCM_TAG_LENGTH,
                    iv
                );

            cipher.init(
                Cipher.DECRYPT_MODE,
                secretKey,
                spec
            );

            byte[] textoPlano =
                cipher.doFinal(cipherText);

            return new String(
                textoPlano,
                StandardCharsets.UTF_8
            );

        } catch (Exception e) {
            throw new IllegalArgumentException(
                "No fue posible descifrar el secreto",
                e
            );
        }
    }

    public String cifrar(String texto) {

    try {
        byte[] iv = new byte[12];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(iv);

        Cipher cipher =
            Cipher.getInstance("AES/GCM/NoPadding");

        GCMParameterSpec spec =
            new GCMParameterSpec(128, iv);

        cipher.init(
            Cipher.ENCRYPT_MODE,
            secretKey,
            spec
        );

        byte[] cifrado =
            cipher.doFinal(
                texto.getBytes(StandardCharsets.UTF_8)
            );

        byte[] resultado =
            new byte[iv.length + cifrado.length];

        System.arraycopy(
            iv,
            0,
            resultado,
            0,
            iv.length
        );

        System.arraycopy(
            cifrado,
            0,
            resultado,
            iv.length,
            cifrado.length
        );

        return Base64.getEncoder()
            .encodeToString(resultado);

    } catch (Exception e) {
        throw new IllegalStateException(
            "Error cifrando dato",
            e
        );
    }
}
}
