import { AddressData } from "./address";
import { CustomerData } from "./customer";

export interface User {
  username: string;
  password: string;
}

export interface AccountData {
  customerData: CustomerData;
  addressData: AddressData;
}
