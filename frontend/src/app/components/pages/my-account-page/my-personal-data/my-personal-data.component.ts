import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CustomerData } from 'src/app/types/customer';
@Component({
  selector: 'app-my-personal-data',
  templateUrl: './my-personal-data.component.html',
  styleUrls: ['./my-personal-data.component.css']
})
export class MyPersonalDataComponent implements OnInit {
  @Input()
  customerData: CustomerData;
  @Output()
  eventEmitter = new EventEmitter<any>();

  detailsForm: FormGroup;
  isInputDisabled: boolean = true;

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {}

  ngOnChanges(changes: SimpleChanges) {
    if (changes['customerData'] !== undefined && changes['customerData']) {
      this.createDetailsForm();
      this.initDetailsForm(this.customerData);
    }
  }

  cancelEdit() {
    this.initDetailsForm(this.customerData);
    this.disableForm();
  }

  editForm() {
    this.firstName?.enable();
    this.lastName?.enable();
    this.email?.enable();
    this.phoneNumber?.enable();
    this.isInputDisabled = false;
  }

  saveDetails() {
    if (!this.detailsForm.invalid) {
      this.disableForm();
      this.eventEmitter.emit({
        eventType: 'SAVE_CUSTOMER_DATA',
        customerData: this.detailsForm.getRawValue()
      });
    }
  }

  private disableForm() {
    this.firstName?.disable();
    this.lastName?.disable();
    this.email?.disable();
    this.phoneNumber?.disable();
    this.isInputDisabled = true;
  }

  private createDetailsForm() {
    this.detailsForm = this.fb.group({
      firstName: [{ value: '', disabled: this.isInputDisabled }, [Validators.required, Validators.pattern('[a-zA-Z]*')]],
      lastName: [{ value: '', disabled: this.isInputDisabled }, [Validators.required, Validators.pattern('[a-zA-Z]*')]],
      email: [{ value: '', disabled: this.isInputDisabled }, [Validators.required, Validators.email]],
      phoneNumber: [{ value: '', disabled: this.isInputDisabled }, [Validators.required, Validators.pattern('[1-9]{1}[0-9]{8}')]],
    })
  }

  private initDetailsForm(customerData: CustomerData) {
    this.detailsForm.setValue(
      {
        firstName: customerData.firstName,
        lastName: customerData.lastName,
        email: customerData.email,
        phoneNumber: customerData.phoneNumber
      }
    )
  }

  get firstName() {
    return this.detailsForm.get('firstName');
  }

  get lastName() {
    return this.detailsForm.get('lastName');
  }

  get email() {
    return this.detailsForm.get('email');
  }

  get phoneNumber() {
    return this.detailsForm.get('phoneNumber');
  }
}
