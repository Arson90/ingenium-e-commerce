import {Injectable} from "@angular/core";
import {Actions, createEffect, ofType} from "@ngrx/effects";
import {AuthService} from "src/app/services/auth/auth.service";
import {catchError, concatMap, map, of, tap} from "rxjs";
import * as AuthAction from '../actions/auth.actions';
import {Router} from "@angular/router";
import {LocalStorageKeys} from "../../services/localStorage/local-storage.service";

@Injectable()
export class AuthApiEffects {
  constructor(private authService: AuthService, private action$: Actions, private router: Router) {}

  tryToLogin$ = createEffect(() => this.action$.pipe(
    ofType(AuthAction.login),
    concatMap((action) => this.authService.login(action.authRequest)
      .pipe(
        map(() => AuthAction.loginSuccess({username: action.authRequest.username})),
        tap(() => this.router.navigate([''])),
        catchError((error) => of(AuthAction.loginFailure({errorMessage: error.error})))
      ))
  ))

  logout$ = createEffect(() => this.action$.pipe(
    ofType(AuthAction.logout),
    tap(() => localStorage.removeItem(LocalStorageKeys.JWT_KEY)),
    tap(() => this.router.navigate(['']))
  ),{dispatch: false}) // to avoid infinite loop, because it hasn't been dispatched any action after first(AuthAction.logout)
}
