import {createFeatureSelector, createSelector} from "@ngrx/store";
import {AuthState} from "../../types/auth";

export const selectAuthState = createFeatureSelector<AuthState>('authState');

export const getUsername = createSelector(
  selectAuthState,
  (state: AuthState) => state.username
)

export const isLoggedIn = createSelector(
  selectAuthState,
  (state: AuthState) => state.loggedIn
)

export const getErrorMessage = createSelector(
  selectAuthState,
  (state: AuthState) => state.errorMessage
)
