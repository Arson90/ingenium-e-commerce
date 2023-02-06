import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {faUser} from "@fortawesome/free-solid-svg-icons";
import {AuthService} from "../../../services/auth/auth.service";
import {UserRequestDTO} from "../../../types/UserRequestDTO";
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
  userRequestDTO: UserRequestDTO = {
    username: "",
    password: "",
    customerRequestDTO: new CustomerRequestDTO()
  };
  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    this.initFormGroup();
  }
  register(data: any) {
    this.authService.register(this.prepareData(data)).subscribe(
      res => console.log('HTTP response', res),
      error => this.errorMessage = error.error
    );
  }
  get username() { return this.formData.get('username'); }
  get password() { return this.formData.get('password'); }
  get firstName() { return this.formData.get('firstName'); }
  get lastName() { return this.formData.get('lastName'); }
  get email() { return this.formData.get('email'); }
  get phoneNumber() { return this.formData.get('phoneNumber'); }

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
  private prepareData(data: any): UserRequestDTO{
    this.userRequestDTO.username = data.username;
    this.userRequestDTO.password = data.password;
    this.userRequestDTO.customerRequestDTO.firstName = data.firstName;
    this.userRequestDTO.customerRequestDTO.lastName = data.lastName;
    this.userRequestDTO.customerRequestDTO.email = data.email;
    this.userRequestDTO.customerRequestDTO.phoneNumber = data.phoneNumber;
    return this.userRequestDTO;
  }
}
