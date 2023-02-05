import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {AuthenticationResponse} from "../../types/AuthenticationResponse";
import {UserRequestDTO} from "../../types/UserRequestDTO";
import {AuthenticationRequest} from "../../types/AuthenticationRequest";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrlAuth = environment.api.AUTHENTICATE;
  private apiUrlRegister = environment.api.REGISTER;
  constructor(private http: HttpClient) { }

  register(userRequestDTO: UserRequestDTO) {
    this.registerUser(userRequestDTO);
  }
  login(request: AuthenticationRequest) {
    return this.http.post<AuthenticationResponse>(`${this.apiUrlAuth}`, request)
  }

  private registerUser(userRequestDTO: UserRequestDTO) {
    console.log(userRequestDTO)
    return this.http.post<void>(`${this.apiUrlRegister}`, userRequestDTO).subscribe(
      res => {console.log("Your account was created, Thanks!")},
      error => {console.log("ERROR", error.status)}
    );
  }

  addTokenToLocalStorage(token: string) {
    localStorage.setItem('id_token', token);
  }

  removeTokenFromLocalStorage() {
    localStorage.removeItem('id_token');
  }
}
