import {createAction, props} from "@ngrx/store";
import {CartEntry} from "../../types/CartEntry";
import {ProductResponseDTO} from "../../types/ProductResponseDTO";

export const addProduct = createAction(
  '[ProductItemComponent] Add a product to cart',
  props<{cartEntry: CartEntry}>()
);

export const removeProduct = createAction(
  '[CartPageComponent] Remove a product from cart',
  props<{productId: number}>()
);

export const changeQuantity = createAction(
  '[CartPageComponent] Change quantity of product',
  props<{quantity: number, product: ProductResponseDTO}>()
);

export const clearCartData = createAction(
  '[ConfirmationComponent] Clear cart data'
);
