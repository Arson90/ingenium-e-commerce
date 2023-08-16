import { createSelector } from "@ngrx/store";
import { AuthState } from "../reducers/auth.reducer";
import { StoreState, getStoreState } from "../state.module";

//Entire Auth state
export const getAuthState = createSelector(
  getStoreState,
  (storeState: StoreState) => storeState.login
)

export const getUsername = createSelector(
  getAuthState,
  (state: AuthState) => state.username
)

export const isLoggedIn = createSelector(
  getAuthState,
  (state: AuthState) => state.loggedIn
)

export const getErrorMessage = createSelector(
  getAuthState,
  (state: AuthState) => state.errorMessage
)
