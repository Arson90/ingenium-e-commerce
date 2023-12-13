import { CustomerData } from "./customer";
import {CustomerRequestDTO} from "./CustomerRequestDTO";

export class AuthenticationRequest {
  username: string
  password: string
}

export interface AuthenticationResponse {
  token: string
}

export interface AuthState {
  username: string | null;
  loggedIn: boolean;
  errorMessage: string | null;
}

export interface RegistrationRequest {
  username: string
  password: string
  customerRequestDTO: CustomerData
}
