import { Injectable } from "@angular/core";
import { Actions, createEffect, ofType } from "@ngrx/effects";
import { AuthService } from "src/app/services/auth/auth.service";
import { map, concatMap, catchError,of, tap } from "rxjs";
import * as AuthAction from '../actions/auth.actions';

@Injectable()
export class AuthApiEffects {
  constructor(private authService: AuthService, private action$: Actions) {}

  tryToLogin$ = createEffect(() => this.action$.pipe(
    ofType(AuthAction.login),
    concatMap((action) => this.authService.login(action.authRequest)
      .pipe(
        map(() => AuthAction.loginSuccess({username: action.authRequest.username})),
        catchError((error) => of(AuthAction.loginFailure({errorMessage: error.error})))
      ))
  ))

  logout$ = createEffect(() => this.action$.pipe(
    ofType(AuthAction.logout),
    tap(() => localStorage.removeItem('JWT_KEY'))
  ))
}
