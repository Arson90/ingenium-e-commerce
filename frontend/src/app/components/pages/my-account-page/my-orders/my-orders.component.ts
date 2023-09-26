import { Component, OnInit } from '@angular/core';
import { OrderData } from "../../../../types/order";
import { OrderService } from "../../../../services/order/order.service";

@Component({
  selector: 'app-my-orders',
  templateUrl: './my-orders.component.html',
  styleUrls: ['./my-orders.component.css']
})
export class MyOrdersComponent implements OnInit {
  orderData: OrderData[];

  constructor(private orderService: OrderService) { }

  ngOnInit(): void {
    this.getMyOrderData();
  }

  getMyOrderData() {
    this.orderService.getMyOrders()
      .subscribe({
        next: (response: OrderData[]) => {
          this.orderData = {...response}
      },
        error: (error) => console.error(error.error, ' ', error.status),
        complete: () => console.info('Order data retrieved')
      })
  }
}
