import {CustomerResponseDTO} from "./CustomerResponseDTO";
import {Money} from "./Money";
import {OrderEntryResponseDTO} from "./OrderEntryResponseDTO";

export interface OrderResponseDTO {
  orderId: number;
  customerResponseDTO: CustomerResponseDTO;
  orderEntriesResponseDTO: OrderEntryResponseDTO[];
  paymentType: string;
  totalPrice: Money;
}
