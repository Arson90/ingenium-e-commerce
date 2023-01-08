import {createAction, props} from "@ngrx/store";
import {BillingAddress} from "../../../types/BillingAddress";

export const saveBillingAddress = createAction(
  '[CheckoutComponent Save billing address',
  props<{billingAddress: BillingAddress}>()
);
