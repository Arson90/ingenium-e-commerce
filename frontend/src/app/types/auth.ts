export class AuthenticationRequest {
  username: string
  password: string
}

export interface AuthenticationResponse {
  token: string;
}
