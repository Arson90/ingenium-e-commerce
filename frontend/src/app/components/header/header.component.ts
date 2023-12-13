import { Component, OnInit } from '@angular/core';
import {Observable} from "rxjs";
import {Store} from "@ngrx/store";
import {Router} from "@angular/router";
import * as AuthAction from 'src/app/stores/actions/auth.actions';
import * as AuthSelector from 'src/app/stores/selectors/auth.selectors'
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  isLoggedIn$: Observable<boolean>;
  constructor(private router: Router, private store: Store) {
    this.isLoggedIn$ = this.store.select(AuthSelector.isLoggedIn);
  }

  ngOnInit(): void {
  }

  logout() {
    this.store.dispatch(AuthAction.logout());
  }
}
