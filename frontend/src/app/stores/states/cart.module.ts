import {NgModule} from "@angular/core";
import {ActionReducer, INIT, MetaReducer, StoreModule, UPDATE} from "@ngrx/store";
import {reducer} from "../reducers/cart.reducer";
import {CartState} from "../../types/cart";
import {LocalStorageKeys} from "../../services/localStorage/local-storage.service";

export const metaReducer = (
  reducer: ActionReducer<CartState>
): ActionReducer<CartState> => {
  return (state, action) => {
    if (action.type === INIT || action.type === UPDATE) {
      const storageValue = localStorage.getItem(LocalStorageKeys.CART_STATE);
      if (storageValue) {
        try {
          return JSON.parse(storageValue);
        } catch {
          localStorage.removeItem(LocalStorageKeys.CART_STATE);
        }
      }
    }
    const nextState = reducer(state, action);
    localStorage.setItem(LocalStorageKeys.CART_STATE, JSON.stringify(nextState));
    return nextState;
  };
};

export const cartReducer: ActionReducer<CartState> = reducer;
export const metaReducers: MetaReducer<CartState>[] = [metaReducer]

@NgModule({
  imports: [
    StoreModule.forFeature('cartState', cartReducer, {metaReducers})
  ]
})
export class CartModule{}
