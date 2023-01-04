import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CartItemsCounterComponent } from './cart-items-counter.component';

describe('CartItemsCounterComponent', () => {
  let component: CartItemsCounterComponent;
  let fixture: ComponentFixture<CartItemsCounterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CartItemsCounterComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CartItemsCounterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
