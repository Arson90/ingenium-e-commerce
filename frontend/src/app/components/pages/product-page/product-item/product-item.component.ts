import {Component, OnInit} from '@angular/core';
import {ProductResponseDTO} from "../../../../types/ProductResponseDTO";
import {ActivatedRoute, Router} from "@angular/router";
import {ProductService} from "../../../../services/product/product.service";
import {Store} from "@ngrx/store";
import {addProduct} from "../../../../stores/cart-store/actions/cart-page.actions";
import {CartEntry} from "../../../../types/CartEntry";

@Component({
  selector: 'app-product-item',
  templateUrl: './product-item.component.html',
  styleUrls: ['./product-item.component.css']
})
export class ProductItemComponent implements OnInit {
  id: number;
  product: ProductResponseDTO;

  constructor(private activatedRoute: ActivatedRoute, private router: Router,
              private productService: ProductService, private store: Store) {
  }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(param => {
      this.id = param['productId'];
    });
    this.getProductById(this.id);
  }

  public getProductById(productId: number): void {
    this.productService.getProductById(productId).subscribe(product => {
      this.product = product;
    })
  }

  public addProduct(product: ProductResponseDTO, quantity: string) {
    const cartEntry = new CartEntry(product,parseInt(quantity));
    this.store.dispatch(addProduct({cartEntry: cartEntry}));
  }
}
