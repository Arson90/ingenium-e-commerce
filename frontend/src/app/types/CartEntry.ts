import {ProductResponseDTO} from "./ProductResponseDTO";

export class CartEntry {
  product: ProductResponseDTO;
  quantity: number;

  constructor(product: ProductResponseDTO, quantity: number) {
    this.product = product;
    this.quantity = quantity;
  }
}
