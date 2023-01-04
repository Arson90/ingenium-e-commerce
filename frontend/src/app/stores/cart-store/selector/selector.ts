import {createFeatureSelector, createSelector} from "@ngrx/store";
import {Cart} from "../../../types/Cart";

//Feature Selectors
export const getCart = createFeatureSelector<Cart>('cart');

//Selectors
export const getCartEntries = createSelector(
  getCart,
  (state: Cart) => state.cartEntries
);

export const getCounter = createSelector(
  getCart,
  (state: Cart) => state.cartEntries.length
);

export const getTotalPrice = createSelector(
  getCart,
  (state: Cart) => {
    let totalPrice = 0;
    state.cartEntries.forEach(cartEntry => {
      let productPrice = cartEntry.product.price.price;
      totalPrice += (productPrice * cartEntry.quantity);
    })
    return totalPrice;
  }
);
