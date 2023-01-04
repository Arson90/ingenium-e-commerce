import { Component, OnInit } from '@angular/core';
import {faTrashAlt} from "@fortawesome/free-solid-svg-icons";
import {Observable} from "rxjs";
import {CartEntry} from "../../../types/CartEntry";
import {Store} from "@ngrx/store";
import {getCartEntries, getCounter, getTotalPrice} from "../../../stores/cart-store/selector/selector";
import {changeQuantity, removeProduct} from "../../../stores/cart-store/actions/cart-page.actions";
import {ProductResponseDTO} from "../../../types/ProductResponseDTO";

@Component({
  selector: 'app-cart-page',
  templateUrl: './cart-page.component.html',
  styleUrls: ['./cart-page.component.css']
})
export class CartPageComponent implements OnInit {
  cartEntries$: Observable<CartEntry[]>;
  total$: Observable<number>;
  counter$: Observable<number>
  trash = faTrashAlt;
  constructor(private store: Store) {
    this.cartEntries$ = store.select(getCartEntries);
    this.total$ = store.select(getTotalPrice);
    this.counter$ = store.select(getCounter);
  }

  ngOnInit(): void {
  }

  public removeProduct(productId: number) {
    this.store.dispatch(removeProduct({productId: productId}));
  }

  public changeQuantity(quantity: string, product: ProductResponseDTO) {
    this.store.dispatch(changeQuantity({quantity: parseInt(quantity), product: product}));
  }
}
