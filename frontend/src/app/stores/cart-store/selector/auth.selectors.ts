import { createFeatureSelector, createSelector } from "@ngrx/store";
import { AuthState } from "../../reducers/auth.reducer";

export const getAuthState = createFeatureSelector<AuthState>('authState');

export const getIsLoggedStatus = createSelector(
  getAuthState,
  (state: AuthState) => state.loggedIn
)
