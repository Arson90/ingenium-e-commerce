import {createFeatureSelector, createSelector} from "@ngrx/store";
import {CheckoutState} from "../../types/checkout";

export const getCheckoutState = createFeatureSelector<CheckoutState>('checkoutState');

export const getFirstName = createSelector(
  getCheckoutState,
  (state: CheckoutState) => state.billingAddress.firstName
)

export const getBillingAddress = createSelector(
  getCheckoutState,
  (state: CheckoutState) => state.billingAddress
);
