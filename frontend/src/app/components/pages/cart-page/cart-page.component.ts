import {Component, OnInit} from '@angular/core';
import {faTrashAlt} from "@fortawesome/free-solid-svg-icons";
import {Observable} from "rxjs";
import {CartEntry} from "../../../types/CartEntry";
import {Store} from "@ngrx/store";
import {ProductResponseDTO} from "../../../types/ProductResponseDTO";
import * as CartAction from "../../../stores/actions/cart.actions"
import * as CartSelectors from "../../../stores/selectors/cart.selectors"

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
    this.cartEntries$ = store.select(CartSelectors.getCartEntries);
    this.total$ = store.select(CartSelectors.getTotalPrice);
    this.counter$ = store.select(CartSelectors.getCounter);
  }

  ngOnInit(): void {
  }

  public removeProduct(productId: number) {
    this.store.dispatch(CartAction.removeProduct({productId: productId}));
  }

  public changeQuantity(quantity: string, product: ProductResponseDTO) {
    this.store.dispatch(CartAction.changeQuantity({quantity: parseInt(quantity), product: product}));
  }
}
