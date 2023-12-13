import {Component, OnInit} from '@angular/core';
import {OrderData} from "../../../types/order";
import {OrderService} from "../../../services/order/order.service";
import {AccountService} from "../../../services/account/account.service";
import {AccountData} from "../../../types/account";

@Component({
  selector: 'app-my-account-page',
  templateUrl: './my-account-page.component.html',
  styleUrls: ['./my-account-page.component.css']
})
export class MyAccountPageComponent implements OnInit {
  orderData: OrderData[];
  accountData: AccountData;
  constructor(private orderService: OrderService, private accountService: AccountService) { }

  ngOnInit(): void {
    this.getMyAccountData();
    this.getMyOrderData();
  }

  getMyAccountData() {
    this.accountService.getMyAccountData()
      .subscribe({
        next: (response: AccountData) => {
          this.accountData = response
          console.log(this.accountData)
        },
        error: (error) => console.error(error.error, ' ', error.status),
        complete: () => console.info('Account data retrieved')
      })
  }

  getMyOrderData() {
    this.orderService.getMyOrders()
      .subscribe({
        next: (response: OrderData[]) => {
          this.orderData = response
          console.log(this.orderData)
        },
        error: (error) => console.error(error.error, ' ', error.status),
        complete: () => console.info('Order data retrieved')
      })
  }
}
