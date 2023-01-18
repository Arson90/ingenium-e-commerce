import {BillingAddress} from "./BillingAddress";
import {PaymentType} from "./PaymentType";

export class BillingAddressBuilder {
  billingAddress: BillingAddress;

  constructor() {
    this.billingAddress = {
      firstName: "",
      lastName: "",
      email: "",
      phoneNumber: "",
      streetName: "",
      streetNumber: "",
      apartmentNumber: "",
      city: "",
      postalCode: "",
      country: "",
      paymentType: ""
    };
  }

  firstName(firstName: string): BillingAddressBuilder {
    this.billingAddress.firstName = firstName;
    return this;
  }

  lastName(lastName: string): BillingAddressBuilder {
    this.billingAddress.lastName = lastName;
    return this;
  }

  email(email: string): BillingAddressBuilder {
    this.billingAddress.email = email;
    return this;
  }

  phoneNumber(phoneNumber: string): BillingAddressBuilder {
    this.billingAddress.phoneNumber = phoneNumber;
    return this;
  }

  streetName(streetName: string): BillingAddressBuilder {
    this.billingAddress.streetName = streetName;
    return this;
  }

  streetNumber(streetNumber: string): BillingAddressBuilder {
    this.billingAddress.streetNumber = streetNumber;
    return this;
  }

  apartmentNumber(apartmentNumber: string): BillingAddressBuilder {
    this.billingAddress.apartmentNumber = apartmentNumber;
    return this;
  }

  city(city: string): BillingAddressBuilder {
    this.billingAddress.city = city;
    return this;
  }

  postalCode(postalCode: string): BillingAddressBuilder {
    this.billingAddress.postalCode = postalCode;
    return this;
  }

  country(country: string): BillingAddressBuilder {
    this.billingAddress.country = country;
    return this;
  }

  paymentType(paymentType: string): BillingAddressBuilder {
    this.billingAddress.paymentType = paymentType;
    return this;
  }

  build(): BillingAddress {
    return this.billingAddress;
  }
}
