import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MyPersonalDataComponent } from './my-personal-data.component';

describe('MyPersonalDataComponent', () => {
  let component: MyPersonalDataComponent;
  let fixture: ComponentFixture<MyPersonalDataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MyPersonalDataComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MyPersonalDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
