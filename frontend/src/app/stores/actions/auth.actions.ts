import { createAction, props} from "@ngrx/store"
import { AuthenticationRequest } from "src/app/types/auth"

export const login = createAction(
  '[Login Page] Login',
  props<{ authRequest: AuthenticationRequest }>()
);

export const loginSuccess = createAction(
  '[Login Page] Login Success',
  props<{ username: string }>()
);

export const loginFailure = createAction(
  '[Login Page] Login failure',
  props<{ errorMessage: string }>()
);

export const logout = createAction(
  '[Login Page] Logout'
);
