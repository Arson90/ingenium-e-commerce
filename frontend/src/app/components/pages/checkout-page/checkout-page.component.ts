import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {Store} from "@ngrx/store";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {BillingAddress} from "../../../types/BillingAddress";
import {BillingAddressBuilder} from "../../../types/BillingAddressBuilder";
import {Router} from "@angular/router";
import {AccountService} from "../../../services/account/account.service";
import * as AuthSelector from "../../../stores/selectors/auth.selectors";
import * as CartSelectors from "../../../stores/selectors/cart.selectors";
import * as CheckoutSelectors from "../../../stores/selectors/checkout.selectors"
import * as CheckoutActions from "../../../stores/actions/checkout.actions"

@Component({
  selector: 'app-checkout-page',
  templateUrl: './checkout-page.component.html',
  styleUrls: ['./checkout-page.component.css']
})

export class CheckoutPageComponent implements OnInit {
  items$: Observable<number>;
  totalPrice$: Observable<number>
  isLoggedIn$: Observable<boolean>;
  formData: FormGroup;
  billingAddress$: Observable<BillingAddress>

  constructor(private store: Store, private router: Router, private accountService: AccountService) {
    this.items$ = store.select(CartSelectors.getCounter);
    this.totalPrice$ = store.select(CartSelectors.getTotalPrice);
    this.isLoggedIn$ = this.store.select(AuthSelector.isLoggedIn);
    this.billingAddress$ = store.select(CheckoutSelectors.getBillingAddress);
  }

  ngOnInit(): void {
    this.initFormGroup();
    this.setDefaultFormGroupValues();
  }

  public onClick(data: any) {
    const billingAddress: BillingAddress = this.buildBillingAddress(data);
    this.store.dispatch(CheckoutActions.saveBillingAddress({billingAddress: billingAddress}));
    return this.router.navigate(['order-summary'])
  }

  private initFormGroup() {
    this.formData = new FormGroup({
      firstName: new FormControl("", [Validators.required, Validators.pattern('[a-zA-Z ]*')]),
      lastName: new FormControl("", [Validators.required, Validators.pattern('[a-zA-Z ]*')]),
      email: new FormControl("", [Validators.required, Validators.email]),
      phoneNumber: new FormControl("", [Validators.required, Validators.pattern('[1-9]{1}[0-9]{8}')]),
      streetName: new FormControl("", [Validators.required, Validators.pattern('[a-zA-Z ]*')]),
      streetNumber: new FormControl("", [Validators.required, Validators.pattern('[1-9]{1}[0-9]*')]),
      apartmentNumber: new FormControl("", [Validators.pattern('[1-9]{1}[0-9]*')]),
      city: new FormControl("", [Validators.required, Validators.pattern('[a-zA-Z ]*')]),
      postalCode: new FormControl("", [Validators.required, Validators.pattern('[1-9]{1}[0-9]{1}\-[0-9]{3}')]),
      country: new FormControl("", [Validators.required, Validators.pattern('[a-zA-Z ]*')]),
      paymentType: new FormControl("", [Validators.required])
    });
  }

  private setDefaultFormGroupValues() {
    this.isLoggedIn$.subscribe({
      next: (value: boolean) => {
        if (value) {
          this.setDefaultValuesForUser();
        } else {
          this.setDefaultValuesForAnonymousUser();
        }
      }
    })
  }

  private setDefaultValuesForUser() {
    this.accountService.getMyAccountData().subscribe(value => {
      let customer = value.customerData;
      let address = value.addressData;
      this.formData.controls['firstName'].setValue(customer.firstName);
      this.formData.controls['lastName'].setValue(customer.lastName);
      this.formData.controls['email'].setValue(customer.email);
      this.formData.controls['phoneNumber'].setValue(customer.phoneNumber);
      this.formData.controls['streetName'].setValue(address.streetName);
      this.formData.controls['streetNumber'].setValue(address.streetNumber);
      this.formData.controls['apartmentNumber'].setValue(address.apartmentNumber);
      this.formData.controls['city'].setValue(address.city);
      this.formData.controls['postalCode'].setValue(address.postalCode);
      this.formData.controls['country'].setValue(address.country);
    })
  }

  private setDefaultValuesForAnonymousUser() {
    console.log('xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx')
    this.billingAddress$.subscribe(value => {
      this.formData.controls['firstName'].setValue(value.firstName);
      this.formData.controls['lastName'].setValue(value.lastName);
      this.formData.controls['email'].setValue(value.email);
      this.formData.controls['phoneNumber'].setValue(value.phoneNumber);
      this.formData.controls['streetName'].setValue(value.streetName);
      this.formData.controls['streetNumber'].setValue(value.streetNumber);
      this.formData.controls['apartmentNumber'].setValue(value.apartmentNumber);
      this.formData.controls['city'].setValue(value.city);
      this.formData.controls['postalCode'].setValue(value.postalCode);
      this.formData.controls['country'].setValue(value.country);
      this.formData.controls['paymentType'].setValue(value.paymentType);
    });
  }

  private buildBillingAddress(data: any): BillingAddress {
    return new BillingAddressBuilder()
      .firstName(data.firstName)
      .lastName(data.lastName)
      .email(data.email)
      .phoneNumber(data.phoneNumber)
      .streetName(data.streetName)
      .streetNumber(data.streetNumber)
      .apartmentNumber(data.apartmentNumber)
      .city(data.city)
      .postalCode(data.postalCode)
      .country(data.country)
      .paymentType(data.paymentType)
      .build();
  }

  get firstName() {
    return this.formData.get('firstName');
  }

  get lastName() {
    return this.formData.get('lastName');
  }

  get email() {
    return this.formData.get('email');
  }

  get phoneNumber() {
    return this.formData.get('phoneNumber');
  }

  get streetName() {
    return this.formData.get('streetName');
  }

  get streetNumber() {
    return this.formData.get('streetNumber');
  }

  get apartmentNumber() {
    return this.formData.get('apartmentNumber');
  }

  get city() {
    return this.formData.get('city');
  }

  get postalCode() {
    return this.formData.get('postalCode');
  }

  get country() {
    return this.formData.get('country');
  }

  get paymentType() {
    return this.formData.get('paymentType');
  }
}
