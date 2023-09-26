import { Component, OnInit } from '@angular/core';
import { Store } from "@ngrx/store";
import { faUser } from "@fortawesome/free-solid-svg-icons";
import { FormControl, FormGroup, Validators } from "@angular/forms";
import { AuthenticationRequest } from "../../../types/auth";
import { Observable } from 'rxjs';
import * as AuthAction from '../../../stores/actions/auth.actions';
import * as AuthSelector from '../../../stores/selectors/auth.selectors'

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {
  errorMessage$: Observable<string | null>;
  account = faUser;
  loginData: FormGroup;

  constructor(private store: Store) {
    this.errorMessage$ = this.store.select(AuthSelector.getErrorMessage);
  }

  ngOnInit(): void {
    this.createLoginForm();
  }

  tryToLogin(data: any) {
    const authenticationRequest: AuthenticationRequest = {
      username: data.username,
      password: data.password
    }
    this.store.dispatch(AuthAction.login({authRequest: authenticationRequest}));
  }

  private createLoginForm() {
    this.loginData = new FormGroup({
      username: new FormControl("", [Validators.required, Validators.pattern('[a-z A-Z 0-9]*')]),
      password: new FormControl("", [Validators.required, Validators.pattern('[a-z A-Z 0-9]{4,16}')]),
    });
  }

  get username() { return this.loginData.get('username'); }
  get password() { return this.loginData.get('password'); }
}
