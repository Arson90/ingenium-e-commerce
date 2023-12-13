import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { environment } from "../../../environments/environment";
import { AccountData } from "../../types/account";
import { CustomerData } from "../../types/customer";
import { AddressData } from "../../types/address";

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  private apiUrl = environment.api.ACCOUNT;

  constructor(private http: HttpClient) { }

  getMyAccountData(): Observable<AccountData> {
    return this.http.get<AccountData>(`${this.apiUrl}/my-account-data`);
  }

  updateAccountCustomer(customerData: CustomerData){
    return this.http.put<void>(`${this.apiUrl}/update-customer`, customerData);
  }

  updateAccountAddress(addressData: AddressData){
    return this.http.put<void>(`${this.apiUrl}/update-address`, addressData);
  }

  changeAccountPassword(password: string){
    return this.http.put<void>(`${this.apiUrl}/change-password`, password);
  }
}
