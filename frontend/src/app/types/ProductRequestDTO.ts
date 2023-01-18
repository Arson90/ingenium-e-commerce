import {Money} from "./Money";

export interface ProductRequestDTO {
  id : number;
  productName : string;
  price : Money;
}
