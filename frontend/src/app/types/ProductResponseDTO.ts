import {Money} from "./Money";

export interface ProductResponseDTO {
  id : number;
  productName : string;
  price : Money;
}
