import {CartEntry} from "./CartEntry";

export interface Cart {
  cartEntries: CartEntry[];
  totalPrice: number;
}
