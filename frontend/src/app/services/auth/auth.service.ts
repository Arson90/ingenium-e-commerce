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

  login(request: AuthenticationRequest) {
    return this.http.post<AuthenticationResponse>(`${this.apiUrlAuth}`, request);
  }

  register(userRequestDTO: UserRequestDTO) {
    return this.http.post<void>(`${this.apiUrlRegister}`, userRequestDTO);
  }

  addUserIdToLocalStorage(userId: string) {
    localStorage.setItem('user_id', userId);
  }
  addTokenToLocalStorage(token: string) {
    localStorage.setItem('id_token', token);
  }

  removeTokenFromLocalStorage() {
    localStorage.removeItem('id_token');
  }

  removeUserIdFromLocalStorage() {
    localStorage.removeItem('user_id');
  }
}
