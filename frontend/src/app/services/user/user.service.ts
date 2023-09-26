import {Injectable} from '@angular/core';
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {UserView} from "../../types/UserView";
import {Observable} from "rxjs";
import {CustomerResponseDTO} from "../../types/CustomerResponseDTO";
import {CustomerUpdateRequestDTO} from "../../types/CustomerUpdateRequestDTO";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private api = environment.api.USER;
  constructor(private http: HttpClient) { }

  getMyUser(): Observable<UserView> {
    return this.http.get<UserView>(`${this.api}/my-user`);
  }

  updateUser(customerUpdateRequestDTO: CustomerUpdateRequestDTO): Observable<CustomerResponseDTO> {
    console.log(customerUpdateRequestDTO)
    return this.http.post<CustomerResponseDTO>(`${this.api}`, {customerUpdateRequestDTO});
  }
}
