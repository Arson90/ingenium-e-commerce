export interface Product {
  id : number;
  productName : string;
  price : Money;
}

export interface Money {
  price: number;
}

export interface ProductData {
  productName : string;
  price : number;
}
