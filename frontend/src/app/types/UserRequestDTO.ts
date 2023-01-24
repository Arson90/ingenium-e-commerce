import {CustomerRequestDTO} from "./CustomerRequestDTO";

export interface UserRequestDTO {
  username: string;
  password: string;
  customerRequestDTO: CustomerRequestDTO;
}
