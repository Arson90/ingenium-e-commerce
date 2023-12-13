import { createReducer, on } from "@ngrx/store"
import * as AuthAction from '../actions/auth.actions';
import {AuthState} from "../../types/auth";

export const initialState: AuthState = {
  username: null,
  loggedIn: false,
  errorMessage: null
}

export const reducer = createReducer(
  initialState,
  on(AuthAction.login, (state, action) => {
    return {
      ...state,
    }
  }),
  on(AuthAction.loginSuccess, (state, action) => {
    return {
      ...state,
      username: action.username,
      loggedIn: true,
      errorMessage: null
    }
  }),
  on(AuthAction.loginFailure, (state, action) => {
    return {
      ...state,
      errorMessage: action.errorMessage
    }
  }),
  on(AuthAction.logout, (state, action) => {
    return {
      ...state,
      username: null,
      loggedIn: false,
      errorMessage: null
    }
  })
)
