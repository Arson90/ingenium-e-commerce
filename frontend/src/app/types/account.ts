import {AddressData} from "./address";
import {CustomerData} from "./customer";

export interface AccountData {
  username: string;
  customerData: CustomerData;
  addressData: AddressData;
}
