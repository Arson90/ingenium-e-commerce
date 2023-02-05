import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../../services/auth/auth.service";
import {Store} from "@ngrx/store";
import {isLogged} from "../../../stores/cart-store/actions/auth.actions";
import {faUser} from "@fortawesome/free-solid-svg-icons";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthenticationRequest} from "../../../types/AuthenticationRequest";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {
  errorMessage: string;
  account = faUser;
  formData: FormGroup;
  authenticationRequest: AuthenticationRequest = {
    username: "",
    password: ""
  };
  constructor(private authService: AuthService, private store: Store, private router: Router) { }

  ngOnInit(): void {
    this.initFormGroup()
  }

  private initFormGroup() {
    this.formData = new FormGroup({
      username: new FormControl("", [Validators.required, Validators.pattern('[a-z A-Z 0-9]*')]),
      password: new FormControl("", [Validators.required, Validators.pattern('[a-z A-Z 0-9]{4,16}')]),
    });
  }

  tryToLogin(data: any) {
    this.authenticationRequest.username = data.username;
    this.authenticationRequest.password = data.password;
    this.authService.login(this.authenticationRequest).subscribe(
      response => {
        this.authService.addTokenToLocalStorage(response.token)
        this.store.dispatch(isLogged({isLogged: true}))
        this.router.navigate([''])
      },
      error => {
        this.errorMessage = error.error
      }
    );
  }

  get username() { return this.formData.get('username'); }
  get password() { return this.formData.get('password'); }
}
