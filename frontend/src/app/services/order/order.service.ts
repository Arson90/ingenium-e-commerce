import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {OrderRequestDTO} from "../../types/OrderRequestDTO";
import {OrderResponseDTO} from "../../types/OrderResponseDTO";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private apiUrl = environment.api.ORDER;
  constructor(private http: HttpClient) { }

  public createOrder(orderRequestDTO: OrderRequestDTO): Observable<OrderResponseDTO> {
    return this.http.post<OrderResponseDTO>(`${this.apiUrl}`, orderRequestDTO);
  }
}
