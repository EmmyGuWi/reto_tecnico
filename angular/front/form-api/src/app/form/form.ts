import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import{Cifrado} from '../services/cifrado';
import Swal from 'sweetalert2';
import { AuthService } from '../services/auth-service';
import { firstValueFrom } from 'rxjs';
@Component({
  selector: 'app-form',
  imports: [FormsModule],
  templateUrl: './form.html',
  styleUrl: './form.css',
})
export class Form {
   operacion: string = '';
   importe: number = 0.0;
   cliente: string = '';
   secreto: string = '';
  mensajeError: string = ''

   constructor(
    private http: HttpClient,
    private router: Router,  private cifradoService: Cifrado,private authService: AuthService
  ) {}

  async form(): Promise<void> {
    this.cliente = this.cliente.trim();
    this.mensajeError = '';

    if (!this.operacion || !this.importe || !this.cliente || !this.secreto) {
      this.mensajeError = 'Todos los campos son obligatorios.';
      return;
    }
    const secretoCifrado =
    await this.cifradoService.cifrar(
      this.secreto
    );


      const formRequest = {

      operacion: this.operacion.trim(),
      importe: this.importe,
      cliente: this.cliente.trim(),
      secreto: secretoCifrado

    };

    console.log('Datos enviados:', formRequest);

    //obtengo el token
      let token: string;

  try {

    const loginResponse = await firstValueFrom(
      this.authService.login()
    );

    token = loginResponse.token;

    if (!token) {

      Swal.fire({
        title: 'Error de autenticación',
        text: 'No se recibió el token de autenticación.',
        icon: 'error'
      });

      return;
    }

  } catch (error) {

    console.error(
      'Error obteniendo token:',
      error
    );

    Swal.fire({
      title: 'Error de autenticación',
      text: 'Ocurrió un error al obtener el token de autenticación.',
      icon: 'error'
    });

    return;
  }

    this.http.post(
    'http://localhost:8082/api/v1/venta',
    formRequest,{observe: 'response',
      headers: {
        Authorization: `Bearer ${token}`
      }
    }
  )
  .subscribe({

    next: response => {

      console.log(
        'Respuesta:',
        response
      );
        if (response.status === 200) {
            console.log('Los datos se mandaroncorrectamente');
              Swal.fire({
              title: 'Datos agregados',
              text: 'Los datos fueron agregados correctamente',
              icon: 'success'
            });
          }

    },

    error: error => {
        Swal.fire({
              title: 'Error',
              text: 'Ocurrio un error inesperado',
              icon: 'error'
            });

      console.error(
        'Error:',
        error
      );

    }

  });

  }

}
