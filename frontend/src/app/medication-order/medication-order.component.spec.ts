import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicationOrderComponent } from './medication-order.component';

describe('MedicationOrderComponent', () => {
  let component: MedicationOrderComponent;
  let fixture: ComponentFixture<MedicationOrderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MedicationOrderComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MedicationOrderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
