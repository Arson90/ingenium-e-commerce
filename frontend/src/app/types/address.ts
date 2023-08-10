export interface Address {
    id: number;
    streetName: string;
    streetNumber: string;
    apartmentNumber: string;
    city: string;
    postalCode: string;
    country: string;
}

export interface ShippingAddress {
    firstName: string;
    lastName: string;
    email: string;
    phoneNumber: string;
    streetName: string;
    streetNumber: string;
    apartmentNumber: string;
    city: string;
    postalCode: string;
    country: string;
    paymentType: string;
}
