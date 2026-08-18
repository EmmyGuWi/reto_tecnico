import { Injectable } from '@angular/core';
import { environment } from '../../enviroments/enviroment';

@Injectable({
  providedIn: 'root',
})
export class Cifrado {

 async cifrar(texto: string): Promise<string> {

    const encoder = new TextEncoder();

    // Convertimos la clave configurada a bytes
    const keyBytes = encoder.encode(environment.aesKey);

    // Importamos los 32 bytes como una clave AES
    const cryptoKey = await crypto.subtle.importKey(
      'raw',
      keyBytes,
      {
        name: 'AES-GCM'
      },
      false,
      ['encrypt']
    );

    // AES-GCM recomienda IV de 12 bytes
    const iv = crypto.getRandomValues(
      new Uint8Array(12)
    );

    // Ciframos
    const encrypted = await crypto.subtle.encrypt(
      {
        name: 'AES-GCM',
        iv: iv
      },
      cryptoKey,
      encoder.encode(texto)
    );

    const encryptedBytes =
      new Uint8Array(encrypted);

    /*
     * Necesitamos enviar también el IV al backend.
     *
     * Construimos:
     *
     * IV + TEXTO_CIFRADO
     */
    const resultado = new Uint8Array(
      iv.length + encryptedBytes.length
    );

    resultado.set(iv, 0);

    resultado.set(
      encryptedBytes,
      iv.length
    );

    // Lo convertimos a Base64 para mandarlo en JSON
    return this.arrayBufferToBase64(resultado);
  }

  private arrayBufferToBase64(
    buffer: Uint8Array
  ): string {

    let binary = '';

    buffer.forEach(byte => {
      binary += String.fromCharCode(byte);
    });

    return btoa(binary);
  }

}
