import {CustomerRequestDTO} from "./CustomerRequestDTO";
import {AddressRequestDTO} from "./AddressRequestDTO";
import {CartEntryRequestDTO} from "./CartEntryRequestDTO";
import {PaymentType} from "./PaymentType";

export interface OrderRequestDTO {
  customerRequestDTO: CustomerRequestDTO;
  addressRequestDTO: AddressRequestDTO;
  paymentType: string;
  cartEntriesRequestDTO: CartEntryRequestDTO[];
}
