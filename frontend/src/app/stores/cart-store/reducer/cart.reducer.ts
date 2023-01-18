import {createReducer, on} from "@ngrx/store";
import {addProduct, changeQuantity, clearCartData, removeProduct} from "../actions/cart-page.actions";
import {CartEntry} from "../../../types/CartEntry";
import {Cart} from "../../../types/Cart";
import {ProductResponseDTO} from "../../../types/ProductResponseDTO";

export const initialCartState: Cart = {
  cartEntries: [],
  totalPrice: 0
}

export const cartReducer = createReducer(
  initialCartState,
  on(addProduct, (state, payload) => {
    return {
      ...state,
      cartEntries: addCartEntry(state.cartEntries, payload.cartEntry),
    }
  }),
  on(removeProduct, (state, payload) => {
    return {
      ...state,
      cartEntries: state.cartEntries.filter(cartEntry => cartEntry.product.id !== payload.productId),
    }
  }),
  on(changeQuantity, (state, payload) => {
    return {
      ...state,
      cartEntries: getQuantity(state.cartEntries, payload.quantity, payload.product)
    }
  }),
  on(clearCartData, (state) => {
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
