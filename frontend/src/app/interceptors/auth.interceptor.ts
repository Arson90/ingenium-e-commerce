import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import {environment} from "../../environments/environment";
import {AuthService} from "../services/auth/auth.service";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  isLoggedIn$: Observable<boolean>;
  constructor(private authService: AuthService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const isToServer = request.url.startsWith(environment.serverUrl);
    if (this.isLoggedIn$ && isToServer) {
      request = request.clone({
        setHeaders: { Authorization: `Bearer ${this.authService.jwt}` }
      });
    }
    return next.handle(request);
  }
}
