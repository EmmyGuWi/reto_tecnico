import { Injectable } from '@angular/core';
import { environment } from '../../enviroments/enviroment';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs';
@Injectable({
  providedIn: 'root',
})
export class AuthService {
   constructor(private http: HttpClient) {}


  login() {

    return this.http.post<{ token: string }>(
      `${environment.apiUrl}/auth/login`,
      environment.auth
    ).pipe(

      tap(response => {
        sessionStorage.setItem(
          'token',
          response.token
        );
      })

    );
  }

}
