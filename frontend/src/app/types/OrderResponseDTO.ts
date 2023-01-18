import {CustomerResponseDTO} from "./CustomerResponseDTO";
import {Money} from "./Money";
import {OrderEntryResponseDTO} from "./OrderEntryResponseDTO";

export interface OrderResponseDTO {
  customerResponseDTO: CustomerResponseDTO;
  orderEntriesResponseDTO: OrderEntryResponseDTO[];
  paymentType: string;
  totalPrice: Money
}
