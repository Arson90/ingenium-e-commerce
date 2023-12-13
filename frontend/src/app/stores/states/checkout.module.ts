import {NgModule} from "@angular/core";
import {ActionReducer, INIT, MetaReducer, StoreModule, UPDATE} from "@ngrx/store";
import {LocalStorageKeys} from "../../services/localStorage/local-storage.service";
import {CheckoutState} from "../../types/checkout";
import {reducer} from "../reducers/checkout.reducer";

export const metaReducer = (
  reducer: ActionReducer<CheckoutState>
): ActionReducer<CheckoutState> => {
  return (state, action) => {
    if (action.type === INIT || action.type === UPDATE) {
      const storageValue = localStorage.getItem(LocalStorageKeys.CHECKOUT_STATE);
      if (storageValue) {
        try {
          return JSON.parse(storageValue);
        } catch {
          localStorage.removeItem(LocalStorageKeys.CHECKOUT_STATE);
        }
      }
    }
    const nextState = reducer(state, action);
    localStorage.setItem(LocalStorageKeys.CHECKOUT_STATE, JSON.stringify(nextState));
    return nextState;
  };
};

export const checkoutReducer: ActionReducer<CheckoutState> = reducer;
export const metaReducers: MetaReducer<CheckoutState>[] = [metaReducer];

@NgModule({
  imports: [
    StoreModule.forFeature('checkoutState', checkoutReducer, {metaReducers})
  ]
})
export class CheckoutModule{}
