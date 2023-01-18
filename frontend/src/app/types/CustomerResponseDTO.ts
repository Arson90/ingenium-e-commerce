import {AddressResponseDTO} from "./AddressResponseDTO";

export interface CustomerResponseDTO {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  phoneNumber: string;
  addressResponseDTO: AddressResponseDTO;
}
