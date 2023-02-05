import {createAction, props} from "@ngrx/store";

export const isLogged = createAction(
  '[LoginComponent] ChangeLoginStatus',
  props<{isLogged: boolean}>()
);
