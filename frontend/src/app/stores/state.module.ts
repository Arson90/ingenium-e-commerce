import { NgModule } from "@angular/core";
import { ActionReducer, ActionReducerMap, INIT, MetaReducer, StoreModule, UPDATE, createFeatureSelector } from "@ngrx/store";
import * as fromLogin from './reducers/auth.reducer';

export interface StoreState {
  login: fromLogin.AuthState;
}

export const reducers: ActionReducerMap<StoreState> = {
  login: fromLogin.reducer
}

export const hydrationMetaReducer = (
  reducer: ActionReducer<StoreState>
): ActionReducer<StoreState> => {
  return (state, action) => {
    if (action.type === INIT || action.type === UPDATE) {
      const storageValue = localStorage.getItem("storeState");
      if (storageValue) {
        try {
          return JSON.parse(storageValue);
        } catch {
          localStorage.removeItem("storeState");
        }
      }
    }
    const nextState = reducer(state, action);
    localStorage.setItem("storeState", JSON.stringify(nextState));
    return nextState;
  };
};

export const metaReducers: MetaReducer<StoreState> [] = [hydrationMetaReducer];

@NgModule({
  imports: [
    StoreModule.forFeature('storeState', reducers, { metaReducers })
  ]
})

export class StateModule {}

//Global state
export const getStoreState = createFeatureSelector<StoreState>('storeState');

