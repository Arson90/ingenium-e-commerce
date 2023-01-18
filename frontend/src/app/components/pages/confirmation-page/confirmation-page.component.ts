import { Component, OnInit } from '@angular/core';
import {Store} from "@ngrx/store";
import {clearBillingAddress} from "../../../stores/cart-store/actions/checkout.actions";
import {clearCartData} from "../../../stores/cart-store/actions/cart-page.actions";

@Component({
  selector: 'app-confirmation-page',
  templateUrl: './confirmation-page.component.html',
  styleUrls: ['./confirmation-page.component.css']
})
export class ConfirmationPageComponent implements OnInit {

  constructor(private store: Store) { }

  ngOnInit(): void {
    this.clearBillingAddressData();
    this.clearCartData();
  }

  public clearBillingAddressData() {
    this.store.dispatch(clearBillingAddress());
  }

  public clearCartData() {
    this.store.dispatch(clearCartData());
  }
}
