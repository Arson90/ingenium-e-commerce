import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ProductPageComponent} from "./components/pages/product-page/product-page.component";
import {ProductItemComponent} from "./components/pages/product-page/product-item/product-item.component";
import {CartPageComponent} from "./components/pages/cart-page/cart-page.component";
import {CheckoutPageComponent} from "./components/pages/checkout-page/checkout-page.component";
import {OrderSummaryComponent} from "./components/pages/order-summary/order-summary.component";
import {ConfirmationPageComponent} from "./components/pages/confirmation-page/confirmation-page.component";
import {LoginPageComponent} from "./components/pages/login-page/login-page.component";
import {RegisterPageComponent} from "./components/pages/register-page/register-page.component";

const routes: Routes = [
  { path: 'cart', component: CartPageComponent},
  { path: 'checkout', component: CheckoutPageComponent},
  { path: 'confirmation', component: ConfirmationPageComponent},
  { path: 'order-summary', component: OrderSummaryComponent},
  { path: 'product-page', component: ProductPageComponent},
  { path: 'product/:productId', component: ProductItemComponent},
  { path: 'login', component: LoginPageComponent},
  { path: 'register', component: RegisterPageComponent},
  { path: '', redirectTo: 'product-page', pathMatch: 'full'}
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes, {enableTracing: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
