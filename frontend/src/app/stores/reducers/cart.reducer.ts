import {createReducer, on} from "@ngrx/store";
import {CartEntry} from "../../types/CartEntry";
import {ProductResponseDTO} from "../../types/ProductResponseDTO";
import * as CartAction from "../../stores/actions/cart.actions"
import {CartState} from "../../types/cart";

export const initialCartState: CartState = {
  cartEntries: [],
  totalPrice: 0
}

export const reducer = createReducer(
  initialCartState,
  on(CartAction.addProduct, (state, payload) => {
    return {
      ...state,
      cartEntries: addCartEntry(state.cartEntries, payload.cartEntry),
    }
  }),
  on(CartAction.removeProduct, (state, payload) => {
    return {
      ...state,
      cartEntries: state.cartEntries.filter(cartEntry => cartEntry.product.id !== payload.productId),
    }
  }),
  on(CartAction.changeQuantity, (state, payload) => {
    return {
      ...state,
      cartEntries: getQuantity(state.cartEntries, payload.quantity, payload.product)
    }
  }),
  on(CartAction.clearCartData, (state) => {
    return {
      ...state,
      cartEntries: [],
      totalPrice: 0
    }
  })
)

const addCartEntry = (cartEntries: CartEntry[], payload: CartEntry) => {
  const cloneCartEntries: CartEntry[] = JSON.parse(JSON.stringify(cartEntries));
  const index = cloneCartEntries
    .map(cartEntry => cartEntry.product.id)
    .indexOf(payload.product.id);

  if (index !== -1) {
    cloneCartEntries[index] = {
      ...payload,
      quantity: cloneCartEntries[index].quantity += payload.quantity
    }
  } else {
    cloneCartEntries.push(payload);
  }
  return cloneCartEntries;
}

const getQuantity = (cartEntries: CartEntry[], quantity: number, product: ProductResponseDTO) => {
  const cloneCartEntries: CartEntry[] = JSON.parse(JSON.stringify(cartEntries));
  const index = cloneCartEntries
    .map(cartEntry => cartEntry.product.id)
    .indexOf(product.id);

  cloneCartEntries[index] = {
    product: product,
    quantity: quantity
  }
  return cloneCartEntries;
}
