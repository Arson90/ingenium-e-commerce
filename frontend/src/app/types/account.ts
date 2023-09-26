import { AddressData } from "./address";
import { CustomerData } from "./customer";

export interface AccountData {
  customerData: CustomerData;
  addressData: AddressData;
}
