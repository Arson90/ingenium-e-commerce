import {createReducer, on} from "@ngrx/store";

import {BillingAddressBuilder} from "../../types/BillingAddressBuilder";
import {CheckoutState} from "../../types/checkout";
import {clearBillingAddress, saveBillingAddress} from "../actions/checkout.actions";

export const initialState: CheckoutState = {
  billingAddress: new BillingAddressBuilder().build()
}

export const reducer = createReducer(
  initialState,
  on(saveBillingAddress, (state, payload) => {
    return {
      ...state,
      billingAddress: payload.billingAddress
    }
  }),
  on(clearBillingAddress, (state) => {
    return {
      ...state,
      billingAddress: new BillingAddressBuilder().build()
    }
  })
);
