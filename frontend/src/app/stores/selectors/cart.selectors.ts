import {createFeatureSelector, createSelector} from "@ngrx/store";
import {CartState} from "../../types/cart";

export const getCartState = createFeatureSelector<CartState>('cartState');

export const getCartEntries = createSelector(
  getCartState,
  (state:CartState) => state.cartEntries
);

export const getCounter = createSelector(
  getCartState,
  (state:CartState) => state.cartEntries.length
);

export const getTotalPrice = createSelector(
  getCartState,
  (state:CartState) => {
    let totalPrice = 0;
    state.cartEntries.forEach(cartEntry => {
      let productPrice = cartEntry.product.price.price;
      totalPrice += (productPrice * cartEntry.quantity);
    })
    return totalPrice;
  }
);
