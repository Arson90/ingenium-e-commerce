import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {ActionReducer, INIT, StoreModule, UPDATE} from "@ngrx/store";
import {AppRoutingModule} from './app-routing.module';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {HttpClientModule} from "@angular/common/http";

import {AppComponent} from './app.component';
import {CartPageComponent} from './components/pages/cart-page/cart-page.component';
import {CartItemsCounterComponent} from "./components/pages/cart-page/cart-items-counter/cart-items-counter.component";
import {FooterComponent} from './components/footer/footer.component';
import {HeaderComponent} from './components/header/header.component';
import {ProductPageComponent} from './components/pages/product-page/product-page.component';
import {ProductItemComponent} from './components/pages/product-page/product-item/product-item.component';
import {StoreDevtoolsModule} from "@ngrx/store-devtools";
import {cartReducer} from "./stores/cart-store/reducer/cart.reducer";
import {CheckoutPageComponent} from './components/pages/checkout-page/checkout-page.component';
import {ReactiveFormsModule} from "@angular/forms";
import {checkoutReducer} from "./stores/cart-store/reducer/checkout.reducer";

export const hydrationMetaReducer = (
  reducer: ActionReducer<any>
): ActionReducer<any> => {
  return (state, action) => {
    if (action.type === INIT || action.type === UPDATE) {
      const storageValue = localStorage.getItem("state");
      if (storageValue) {
        try {
          return JSON.parse(storageValue);
        } catch {
          localStorage.removeItem("state");
        }
      }
    }
    const nextState = reducer(state, action);
    localStorage.setItem("state", JSON.stringify(nextState));
    return nextState;
  };
};

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    CartPageComponent,
    CartItemsCounterComponent,
    ProductPageComponent,
    ProductItemComponent,
    CheckoutPageComponent,
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    HttpClientModule,
    FontAwesomeModule,
    StoreModule.forRoot({cart: cartReducer, billingAddress: checkoutReducer}, {metaReducers: [hydrationMetaReducer]}),
    StoreDevtoolsModule.instrument({
      maxAge: 15
    }),
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
