import { Component, OnInit } from '@angular/core';
import { faStar } from "@fortawesome/free-solid-svg-icons";
import { ProductService } from "../../../services/product/product.service";
import { Product } from "../../../types/product";

@Component({
  selector: 'app-product-page',
  templateUrl: './product-page.component.html',
  styleUrls: ['./product-page.component.css']
})
export class ProductPageComponent implements OnInit {
  products: Product[];
  star = faStar;
  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.getProducts();
  }

  public getProducts(): void {
    this.productService.getProducts()
      .subscribe({
        next: (response: Product[])  => this.products = response,
        error: (error) => console.error(error.error, ' ', error.status)
      })
  }
}
