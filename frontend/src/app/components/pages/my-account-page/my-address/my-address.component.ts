import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AddressData } from 'src/app/types/address';

@Component({
  selector: 'app-my-address',
  templateUrl: './my-address.component.html',
  styleUrls: ['./my-address.component.css']
})
export class MyAddressComponent implements OnInit {
  @Input()
  addressData: AddressData;
  @Output()
  eventEmitter = new EventEmitter<any>();

  addressForm: FormGroup;
  isInputDisabled: boolean = true;

  constructor() { }

  ngOnInit(): void {
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['addressData'] !== undefined && changes['addressData']) {
      this.createDetailsForm();
      this.initDetailsForm(this.addressData);
    }
  }

  cancelEdit() {
    this.initDetailsForm(this.addressData);
    this.disableForm();
  }

  editForm() {
    this.streetName?.enable();
    this.streetNumber?.enable();
    this.apartmentNumber?.enable();
    this.city?.enable();
    this.postalCode?.enable();
    this.country?.enable();
    this.isInputDisabled = false;
  }

  saveAddress() {
    if (!this.addressForm.invalid) {
      this.disableForm();
      this.eventEmitter.emit({
        eventType: 'SAVE_ADDRESS_DATA',
        addressData: this.addressForm.getRawValue()
      });
    }
  }

  private disableForm() {
    this.streetName?.disable();
    this.streetNumber?.disable();
    this.apartmentNumber?.disable();
    this.city?.disable();
    this.postalCode?.disable();
    this.country?.disable();
    this.isInputDisabled = true;
  }

  private createDetailsForm() {
    this.addressForm = new FormGroup({
      streetName: new FormControl({value: '', disabled: this.isInputDisabled}, [Validators.required, Validators.pattern('[a-zA-Z ]*')]),
      streetNumber: new FormControl({value: '', disabled: this.isInputDisabled}, [Validators.required, Validators.pattern('[1-9]{1}[0-9]*')]),
      apartmentNumber: new FormControl({value: '', disabled: this.isInputDisabled}, [Validators.pattern('[1-9]{1}[0-9]*')]),
      city: new FormControl({value: '', disabled: this.isInputDisabled}, [Validators.required, Validators.pattern('[a-zA-Z ]*')]),
      postalCode: new FormControl({value: '', disabled: this.isInputDisabled}, [Validators.required, Validators.pattern('[1-9]{1}[0-9]{1}\-[0-9]{3}')]),
      country: new FormControl({value: '', disabled: this.isInputDisabled}, [Validators.required, Validators.pattern('[a-zA-Z ]*')])
    })
  }
  private initDetailsForm(addressData: AddressData) {
    this.addressForm.setValue({
      streetName: addressData.streetName,
      streetNumber: addressData.streetNumber,
      apartmentNumber: addressData.apartmentNumber,
      city: addressData.city,
      postalCode: addressData.postalCode,
      country: addressData.country
    })
  }

  get streetName() { return this.addressForm.get('streetName'); }

  get streetNumber() { return this.addressForm.get('streetNumber'); }

  get apartmentNumber() { return this.addressForm.get('apartmentNumber'); }

  get city() { return this.addressForm.get('city'); }

  get postalCode() { return this.addressForm.get('postalCode'); }

  get country() { return this.addressForm.get('country'); }
}
