import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from "../../environments/environment";
import {LocalStorageService} from "../services/localStorage/local-storage.service";
import * as AuthSelectors from "../stores/selectors/auth.selectors"
import {Store} from "@ngrx/store";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  isLoggedIn$: Observable<boolean>;
  constructor(private localStorageService: LocalStorageService, private store: Store) {
    this.isLoggedIn$ = this.store.select(AuthSelectors.isLoggedIn);
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const isToServer = request.url.startsWith(environment.serverUrl);
    if (this.isLoggedIn$ && isToServer) {
      request = request.clone({
        setHeaders: { Authorization: `Bearer ${this.localStorageService.jwt}` }
      });
    }
    return next.handle(request);
  }
}
