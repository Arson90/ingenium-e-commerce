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
import {CheckoutPageComponent} from './components/pages/checkout-page/checkout-page.component';
import {ReactiveFormsModule} from "@angular/forms";
import {OrderSummaryComponent} from "./components/pages/order-summary/order-summary.component";
import {ConfirmationPageComponent} from './components/pages/confirmation-page/confirmation-page.component';
import {LoginPageComponent} from './components/pages/login-page/login-page.component';
import {RegisterPageComponent} from './components/pages/register-page/register-page.component';
import {AuthInterceptor} from "./interceptors/auth.interceptor";
import {MyAccountPageComponent} from './components/pages/my-account-page/my-account-page.component';
import {ChangePasswordComponent} from './components/pages/my-account-page/change-password/change-password.component';
import {MyAddressComponent} from './components/pages/my-account-page/my-address/my-address.component';
import {MyOrdersComponent} from './components/pages/my-account-page/my-orders/my-orders.component';
import {MyProfileComponent} from './components/pages/my-account-page/my-profile/my-profile.component';
import {MyPersonalDataComponent} from './components/pages/my-account-page/my-personal-data/my-personal-data.component';
import {AuthModule} from "./stores/states/auth.module";
import {EffectsModule} from "@ngrx/effects";
import {AuthApiEffects} from "./stores/effects/auth.effects";
import {CartModule} from "./stores/states/cart.module";
import {CheckoutModule} from "./stores/states/checkout.module";

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
    MyAccountPageComponent,
    ChangePasswordComponent,
    MyAddressComponent,
    MyOrdersComponent,
    MyProfileComponent,
    MyPersonalDataComponent,
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    HttpClientModule,
    FontAwesomeModule,
    StoreModule.forRoot({}),
    AuthModule,
    CartModule,
    CheckoutModule,
    StoreDevtoolsModule.instrument({
      maxAge: 15
    }),
    ReactiveFormsModule,
    EffectsModule.forRoot(),
    EffectsModule.forFeature([AuthApiEffects])
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
