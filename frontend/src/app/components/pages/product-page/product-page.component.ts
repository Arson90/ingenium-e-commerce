import { Component, OnInit } from '@angular/core';
import {ProductResponseDTO} from "../../../types/ProductResponseDTO";
import {faStar} from "@fortawesome/free-solid-svg-icons";
import {ProductService} from "../../../services/product/product.service";

@Component({
  selector: 'app-product-page',
  templateUrl: './product-page.component.html',
  styleUrls: ['./product-page.component.css']
})
export class ProductPageComponent implements OnInit {
  products: ProductResponseDTO[];
  star = faStar;
  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.getProducts();
  }

  public getProducts(): void {
    this.productService.getProducts().subscribe((products) => this.products = products);
  }
}
