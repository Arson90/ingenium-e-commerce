import {createReducer, on} from "@ngrx/store";
import {isLogged} from "../actions/auth.actions";

export interface AuthState {
  isLogged: boolean;
}

export const initialAuthState: AuthState = {
  isLogged: false
}

export const authReducer = createReducer(
  initialAuthState,
  on(isLogged, (state, payload) => {
    return {
      ...state,
      isLogged: payload.isLogged
    }
  })
);
