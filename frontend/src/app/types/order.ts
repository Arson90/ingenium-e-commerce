import { AddressData, ShippingAddress } from "./address";
import { CustomerData } from "./customer";
import { Money, Product, ProductData } from "./product";

export interface Order {
  id: number;
  date: Date;
  shippingAddress: ShippingAddress;
  orderEntries: OrderEntry[];
  totalPrice: Money;
}

export interface OrderData {
  orderId: number;
  orderDate: Date;
  customerData: CustomerData;
  addressData: AddressData;
  orderEntriesData: OrderEntryData[];
  totalPrice: number;
}

export interface OrderEntry {
  id: number;
  product: Product;
  quantity: number;
}

export interface OrderEntryData {
  product: ProductData;
  quantity: number;
}

export interface OrderResponse {
  id: number;
  email: string;
}
