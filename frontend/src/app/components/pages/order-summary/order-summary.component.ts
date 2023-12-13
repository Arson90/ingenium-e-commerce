import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {BillingAddress} from "../../../types/BillingAddress";
import {Store} from "@ngrx/store";
import {BillingAddressBuilder} from "../../../types/BillingAddressBuilder";
import {CartEntry} from "../../../types/CartEntry";
import {faPaypal} from "@fortawesome/free-brands-svg-icons";
import {faMoneyBillAlt} from "@fortawesome/free-regular-svg-icons/faMoneyBillAlt";
import {faCreditCard} from "@fortawesome/free-solid-svg-icons/faCreditCard";
import {CustomerRequestDTO} from "../../../types/CustomerRequestDTO";
import {AddressRequestDTO} from "../../../types/AddressRequestDTO";
import {CartEntryRequestDTO} from "../../../types/CartEntryRequestDTO";
import {OrderRequestDTO} from "../../../types/OrderRequestDTO";
import {CartRequestDTO} from "../../../types/CartRequestDTO";
import {OrderService} from "../../../services/order/order.service";
import {OrderResponseDTO} from "../../../types/OrderResponseDTO";
import {ActivatedRoute, Router} from "@angular/router";
import * as CartSelectors from "../../../stores/selectors/cart.selectors"
import * as CheckoutSelectors from "../../../stores/selectors/checkout.selectors"

@Component({
  selector: 'app-order-summary',
  templateUrl: './order-summary.component.html',
  styleUrls: ['./order-summary.component.css']
})
export class OrderSummaryComponent implements OnInit {
  creditCard = faCreditCard;
  paypal = faPaypal;
  cash = faMoneyBillAlt;
  billingAddress$: Observable<BillingAddress>
  billingAddress: BillingAddress = new BillingAddressBuilder().build()
  cartEntries$: Observable<CartEntry[]>;
  cartRequestDTO: CartRequestDTO = {
    cartEntriesRequestDTO: []
  };
  orderResponseDTO: OrderResponseDTO;
  total$: Observable<number>;
  counter$: Observable<number>

  constructor(private store: Store, private orderService: OrderService, private router: Router, private activatedRoute: ActivatedRoute,) {
    this.billingAddress$ = store.select(CheckoutSelectors.getBillingAddress);
    this.cartEntries$ = store.select(CartSelectors.getCartEntries);
    this.total$ = store.select(CartSelectors.getTotalPrice);
    this.counter$ = store.select(CartSelectors.getCounter);
  }

  ngOnInit(): void {
    this.billingAddress$.subscribe(value => {
      this.billingAddress = value;
    })
  }

  public createOrder() {
    const customerRequestDTO: CustomerRequestDTO = this.createCustomer();
    const addressRequestDTO: AddressRequestDTO = this.createAddress();
    const paymentType: string = this.billingAddress.paymentType;
    const cartEntries: CartEntryRequestDTO[] = this.createCartEntries();

    const orderRequestDTO: OrderRequestDTO = {
      customerRequestDTO: customerRequestDTO,
      addressRequestDTO: addressRequestDTO,
      paymentType: paymentType,
      cartEntriesRequestDTO: cartEntries,
    }

    this.orderService.createOrder(orderRequestDTO).subscribe(response => {
      this.orderResponseDTO = response;
    });
    this.router.navigate(['confirmation'])
      .then(nav => {
        console.log(nav);
      }, err => {
        console.log(err)
      });
  }

  private createCustomer(): CustomerRequestDTO {
    return {
      firstName: this.billingAddress.firstName,
      lastName: this.billingAddress.lastName,
      email: this.billingAddress.email,
      phoneNumber: this.billingAddress.phoneNumber
    };
  }

  private createAddress(): AddressRequestDTO {
    return {
      streetName: this.billingAddress.streetName,
      streetNumber: this.billingAddress.streetNumber,
      apartmentNumber: this.billingAddress.apartmentNumber,
      city: this.billingAddress.city,
      postalCode: this.billingAddress.postalCode,
      country: this.billingAddress.country
    }
  }

  private createCartEntries(): CartEntryRequestDTO[] {
    let cartEntries: CartEntry[] = [];
    this.cartEntries$.subscribe(value => {cartEntries = value})
    return this.convertCartEntryToCartEntryRequestDto(cartEntries)
  }

  private convertCartEntryToCartEntryRequestDto(cartEntries: CartEntry[]): CartEntryRequestDTO[]{
    cartEntries.forEach(cartEntry => {
      const cartEntryRequestDto: CartEntryRequestDTO = {
        productId: cartEntry.product.id,
        quantity: cartEntry.quantity,
      }
      this.cartRequestDTO.cartEntriesRequestDTO.push(cartEntryRequestDto);
    })
    return this.cartRequestDTO.cartEntriesRequestDTO;
  }
}
