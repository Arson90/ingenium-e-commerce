import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable, tap} from 'rxjs';
import {AuthenticationRequest, AuthenticationResponse, RegistrationRequest} from "../../types/auth";
import {LocalStorageService} from "../localStorage/local-storage.service";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrlAuth = environment.api.AUTHENTICATE;
  private apiUrlRegister = environment.api.REGISTER;

  constructor(private http: HttpClient, private localStorageService: LocalStorageService) {
  }

  login(request: AuthenticationRequest): Observable<AuthenticationResponse> {
    return this.http.post<AuthenticationResponse>(`${this.apiUrlAuth}`, request)
      .pipe(
        tap(response => this.localStorageService.jwt = response.token)
      );
  }

  register(registrationRequest: RegistrationRequest) {
    console.log(registrationRequest)
    return this.http.post<void>(`${this.apiUrlRegister}`, registrationRequest);
  }
}
