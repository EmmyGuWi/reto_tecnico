import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-loggin',
  imports: [ FormsModule],
  templateUrl: './loggin.html',
  styleUrl: './loggin.css',
})
export class Loggin {
  usuario: string = '';
  password: string = '';

  mensajeError: string = '';
  cargando: boolean = false;

  constructor(
    private http: HttpClient,
    private router: Router
  ) {}

  login(): void {

    this.mensajeError = '';

    // Validaciones básicas del frontend
    if (!this.usuario || !this.password) {

      this.mensajeError =
        'Ingresa usuario y contraseña.';

      return;
    }

    if (
      this.password.length < 8 ||
      this.password.length > 20
    ) {

      this.mensajeError =
        'La contraseña debe tener entre 8 y 20 caracteres.';

      return;
    }

    const loginRequest = {

      usuario: this.usuario,
      password: this.password

    };

    console.log('Datos enviados:', loginRequest);

    this.cargando = true;

    this.http.post(
      'http://localhost:8080/auth/login',
      loginRequest
    )
    .subscribe({

      next: (response) => {

        console.log(
          'Respuesta del servidor:',
          response
        );

        this.cargando = false;

        // Login correcto
        this.router.navigate(['/form']);

      },

      error: (error) => {

        console.error(
          'Error del login:',
          error
        );

        this.cargando = false;

        if (error.status === 401) {

          this.mensajeError =
            'Usuario o contraseña incorrectos.';

        } else {

          this.mensajeError =
            'No se pudo conectar con el servidor.';

        }

      }

    });

  }
}
