import { Component, OnInit } from '@angular/core';
import {Observable} from "rxjs";
import {Store} from "@ngrx/store";
import {getIsLoggedStatus} from "../../stores/cart-store/selector/auth.selectors";
import {isLogged} from "../../stores/cart-store/actions/auth.actions";
import {AuthService} from "../../services/auth/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  isLogged$: Observable<boolean>
  constructor(private authService: AuthService, private router: Router, private store: Store) {
    this.isLogged$ = store.select(getIsLoggedStatus);
  }

  ngOnInit(): void {
  }

  logout() {
    this.authService.removeUserIdFromLocalStorage();
    this.authService.removeTokenFromLocalStorage();
    this.store.dispatch(isLogged({isLogged: false}));
    this.router.navigate([''])
  }
}
