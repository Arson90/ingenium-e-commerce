import {Component, OnInit} from '@angular/core';
import {faShoppingCart} from "@fortawesome/free-solid-svg-icons";
import {Observable} from "rxjs";
import {Store} from "@ngrx/store";
import {getCounter} from "../../../../stores/cart-store/selector/selector";

@Component({
  selector: 'app-cart-items-counter',
  templateUrl: './cart-items-counter.component.html',
  styleUrls: ['./cart-items-counter.component.css']
})
export class CartItemsCounterComponent implements OnInit {
  shoppingCart = faShoppingCart;
  counter$: Observable<number>;
  constructor(private store: Store) {
    this.counter$ = store.select(getCounter);
  }

  ngOnInit(): void {
  }
}
