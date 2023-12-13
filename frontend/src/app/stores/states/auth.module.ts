import {NgModule} from "@angular/core";
import {ActionReducer, INIT, MetaReducer, StoreModule, UPDATE} from "@ngrx/store";
import {reducer} from "../reducers/auth.reducer";
import {LocalStorageKeys} from "../../services/localStorage/local-storage.service";
import {AuthState} from "../../types/auth";

// export const metaReducer = (
//   reducer: ActionReducer<AuthState>
// ): ActionReducer<AuthState> => {
//   return (state, action) => {
//     if (action.type === INIT || action.type === UPDATE) {
//       test.getState(LocalStorageKeys.AUTH_STATE);
//     }
//     const nextState = reducer(state, action);
//     test.setState(nextState, LocalStorageKeys.AUTH_STATE);
//     return nextState;
//   };
// };

export const metaReducer = (
  reducer: ActionReducer<AuthState>
): ActionReducer<AuthState> => {
  return (state, action) => {
    if (action.type === INIT || action.type === UPDATE) {
      const storageValue = localStorage.getItem(LocalStorageKeys.AUTH_STATE);
      if (storageValue) {
        try {
          return JSON.parse(storageValue);
        } catch {
          localStorage.removeItem(LocalStorageKeys.AUTH_STATE);
        }
      }
    }
    const nextState = reducer(state, action);
    localStorage.setItem(LocalStorageKeys.AUTH_STATE, JSON.stringify(nextState));
    return nextState;
  };
};

export const authReducer: ActionReducer<AuthState> = reducer;
export const metaReducers: MetaReducer<AuthState>[] = [metaReducer];

@NgModule({
  imports: [
    StoreModule.forFeature('authState', authReducer, {metaReducers})
  ]
})
export class AuthModule{}
