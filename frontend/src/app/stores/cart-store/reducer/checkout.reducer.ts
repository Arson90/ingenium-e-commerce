import {BillingAddress} from "../../../types/BillingAddress";
import {createReducer, on} from "@ngrx/store";
import {saveBillingAddress} from "../actions/checkout.actions";
import {BillingAddressBuilder} from "../../../types/BillingAddressBuilder";

export interface CheckoutState {
  billingAddress: BillingAddress;
}
export const initialState: CheckoutState = {
  billingAddress: new BillingAddressBuilder().build()
}

export const checkoutReducer = createReducer(
  initialState,
  on(saveBillingAddress, (state, payload) => {
    return {
      ...state,
      billingAddress: payload.billingAddress
    }
  })
);
