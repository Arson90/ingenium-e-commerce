import {Component, Input} from '@angular/core';
import {OrderData} from "../../../../types/order";

@Component({
  selector: 'app-my-orders',
  templateUrl: './my-orders.component.html',
  styleUrls: ['./my-orders.component.css']
})
export class MyOrdersComponent {
  @Input()
  orderData: OrderData[];
}
