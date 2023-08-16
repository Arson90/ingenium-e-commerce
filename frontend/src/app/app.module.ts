import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {StoreModule} from "@ngrx/store";
import {AppRoutingModule} from './app-routing.module';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";

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
import {OrderSummaryComponent} from "./components/pages/order-summary/order-summary.component";
import { ConfirmationPageComponent } from './components/pages/confirmation-page/confirmation-page.component';
import { LoginPageComponent } from './components/pages/login-page/login-page.component';
import { RegisterPageComponent } from './components/pages/register-page/register-page.component';
import {authReducer} from "./stores/cart-store/reducer/auth.reducer";
import { AuthInterceptor } from "./interceptors/auth.interceptor";

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
    OrderSummaryComponent,
    ConfirmationPageComponent,
    LoginPageComponent,
    RegisterPageComponent,
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    HttpClientModule,
    FontAwesomeModule,
    StoreModule.forRoot({cart: cartReducer, billingAddress: checkoutReducer, authState: authReducer}),
    StoreDevtoolsModule.instrument({
      maxAge: 15
    }),
    ReactiveFormsModule,

  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
