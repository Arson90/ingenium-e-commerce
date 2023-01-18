import {ProductResponseDTO} from "./ProductResponseDTO";

export interface OrderEntryResponseDTO {
  id: number;
  productResponseDTO: ProductResponseDTO;
  quantity: number;
}
