import {Component, OnInit} from '@angular/core';
import {Store} from "@ngrx/store";
import {clearBillingAddress} from "../../../stores/actions/checkout.actions";
import * as CartAction from "../../../stores/actions/cart.actions"

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

  private clearBillingAddressData() {
    this.store.dispatch(clearBillingAddress());
  }

  private clearCartData() {
    this.store.dispatch(CartAction.clearCartData());
  }
}
