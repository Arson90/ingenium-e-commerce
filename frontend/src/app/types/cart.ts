import {CartEntry} from "./CartEntry";

export interface CartState {
  cartEntries: CartEntry[];
  totalPrice: number;
}
