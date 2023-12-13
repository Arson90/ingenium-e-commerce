import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from "@angular/forms";
import { faUser } from "@fortawesome/free-solid-svg-icons";
import { AuthService } from "../../../services/auth/auth.service";
import { RegistrationRequest } from "../../../types/auth";
import { CustomerData } from "../../../types/customer";
import {CustomerRequestDTO} from "../../../types/CustomerRequestDTO";

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css']
})
export class RegisterPageComponent implements OnInit {
  account = faUser;
  formData: FormGroup;
  errorMessage: string;

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    this.initFormGroup();
  }

  //TODO: create new confirmation register page and navigate to it
  tryToRegister(data: any) {
    this.authService.register(this.prepareData(data))
      .subscribe({
        next: () => console.info('HTTP response 200'),
        error: (error) => this.errorMessage = error.error
      }
    );
  }

  private initFormGroup() {
    this.formData = new FormGroup({
      username: new FormControl("", [Validators.required, Validators.pattern('[a-z A-Z 0-9]*')]),
      password: new FormControl("", [Validators.required, Validators.pattern('[a-z A-Z 0-9]{8,16}')]),
      firstName: new FormControl("",[Validators.required, Validators.pattern('[a-zA-Z ]*')]),
      lastName: new FormControl("", [Validators.required, Validators.pattern('[a-zA-Z ]*')]),
      email: new FormControl("", [Validators.required, Validators.email]),
      phoneNumber: new FormControl("", [Validators.required, Validators.pattern('[1-9]{1}[0-9]{8}')]),
    });
  }

  private prepareData(data: any): RegistrationRequest{
    const customerData: CustomerData = {
      firstName: data.firstName,
      lastName: data.lastName,
      email: data.email,
      phoneNumber: data.phoneNumber
    }
    return {
      username: data.username,
      password: data.password,
      customerRequestDTO: customerData
    };
  }

  get username() { return this.formData.get('username'); }
  get password() { return this.formData.get('password'); }
  get firstName() { return this.formData.get('firstName'); }
  get lastName() { return this.formData.get('lastName'); }
  get email() { return this.formData.get('email'); }
  get phoneNumber() { return this.formData.get('phoneNumber'); }
}
