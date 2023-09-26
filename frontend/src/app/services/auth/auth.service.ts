import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { environment } from "../../../environments/environment";
import { Observable, tap } from 'rxjs';
import { UserRequestDTO } from "../../types/UserRequestDTO";
import { AuthenticationRequest, AuthenticationResponse } from "../../types/auth";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private static readonly KEY = 'JWT_KEY';
  private apiUrlAuth = environment.api.AUTHENTICATE;
  private apiUrlRegister = environment.api.REGISTER;
  constructor(private http: HttpClient) { }

  login(request: AuthenticationRequest): Observable<AuthenticationResponse> {
    return this.http.post<AuthenticationResponse>(`${this.apiUrlAuth}`, request)
        .pipe(
            tap(response => this.jwt = response.token)
        );
  }

  register(userRequestDTO: UserRequestDTO) {
    return this.http.post<void>(`${this.apiUrlRegister}`, userRequestDTO);
  }

  get jwt(): string {
    return localStorage.getItem(AuthService.KEY) ?? '';
  }

  private set jwt(value: string) {
    localStorage.setItem(AuthService.KEY, value);
  }
}
