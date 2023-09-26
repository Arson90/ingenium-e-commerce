import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { ProductResponseDTO } from "../../types/ProductResponseDTO";
import {environment} from "../../../environments/environment";
import {Product} from "../../types/product";

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private apiUrl = environment.api.PRODUCTS;
  constructor(private http: HttpClient) { }

  public getProducts(): Observable<ProductResponseDTO[]> {
    return this.http.get<ProductResponseDTO[]>(`${this.apiUrl}`);
  }

  public getProductById(productId: number): Observable<Product> {
    return this.http.get<Product>(`${this.apiUrl}/${productId}`);
  }
}
