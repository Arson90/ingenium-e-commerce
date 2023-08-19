import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-change-passowrd',
  templateUrl: './change-passowrd.component.html',
  styleUrls: ['./change-passowrd.component.css']
})
export class ChangePassowrdComponent implements OnInit {
  @Output()
  eventEmitter = new EventEmitter<any>();

  isInputDisabled: boolean = true;
  passwordForm: FormGroup;


  constructor() { }

  ngOnInit(): void {
    this.createPasswordForm();
  }

  cancelEdit() {
    this.initPasswordForm();
    this.disableForm();
  }

  savePassword() {
    if (!this.passwordForm.invalid) {
      this.disableForm();
      this.eventEmitter.emit({
        eventType: 'SAVE_PASSWORD',
        passwordData: this.passwordForm.getRawValue()
      });
      this.initPasswordForm();
    }
  }

  editForm() {
    this.newPassword?.enable();
    this.repeatedPassword?.enable();
    this.isInputDisabled = false;
  }

  private disableForm() {
    this.newPassword?.disable();
    this.repeatedPassword?.disable();
    this.isInputDisabled = true;
  }

  private createPasswordForm() {
    this.passwordForm = new FormGroup({
      newPassword: new FormControl({ value: '', disabled: this.isInputDisabled }, [Validators.required, Validators.pattern('[a-z A-Z 0-9]*')]),
      repeatedPassword: new FormControl({ value: '', disabled: this.isInputDisabled }, [Validators.required, Validators.pattern('[a-z A-Z 0-9]{4,16}')])

    })
  }

  private initPasswordForm() {
    this.passwordForm.setValue({
      newPassword: '',
      repeatedPassword: ''
    })
  }

  get newPassword() {
    return this.passwordForm.get('newPassword');
  }

  get repeatedPassword() {
    return this.passwordForm.get('repeatedPassword');
  }
}
