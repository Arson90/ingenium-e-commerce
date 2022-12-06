import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ProductMainPageComponent} from "./components/pages/product-main-page/product-main-page.component";

const routes: Routes = [
  { path: 'products-main-page', component: ProductMainPageComponent},
  { path: '', redirectTo: 'products-main-page', pathMatch: 'full'}
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes, {enableTracing: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
