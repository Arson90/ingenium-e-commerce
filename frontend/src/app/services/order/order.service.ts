import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { OrderRequestDTO } from "../../types/OrderRequestDTO";
import { OrderResponseDTO } from "../../types/OrderResponseDTO";
import { environment } from "../../../environments/environment";
import { OrderData } from "../../types/order";

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private apiUrl = environment.api.ORDER;
  constructor(private http: HttpClient) { }

  createOrder(orderRequestDTO: OrderRequestDTO): Observable<OrderResponseDTO> {
    return this.http.post<OrderResponseDTO>(`${this.apiUrl}`, orderRequestDTO);
  }

  getMyOrders(): Observable<OrderData[]> {
    return this.http.get<OrderData[]>(`${this.apiUrl}/my-orders`);
  }
}
