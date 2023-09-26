import { CustomerData } from "./customer";

export class AuthenticationRequest {
  username: string
  password: string
}

export interface AuthenticationResponse {
  token: string
}

export interface RegistrationRequest {
  username: string
  password: string
  customerData: CustomerData
}
