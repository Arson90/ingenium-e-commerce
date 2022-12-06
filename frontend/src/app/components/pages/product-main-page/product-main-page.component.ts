import { Component, OnInit } from '@angular/core';
import { Product } from "../../../types/product";
import { ProductService } from "../../../services/product/product.service";

@Component({
  selector: 'app-product-main-page',
  templateUrl: './product-main-page.component.html',
  styleUrls: ['./product-main-page.component.css']
})
export class ProductMainPageComponent implements OnInit {
  products: Product[];
  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.getProducts();
  }

  public getProducts(): void {
    this.productService.getProducts().subscribe((products) => this.products = products);
  }
}
