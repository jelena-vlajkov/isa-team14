import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicationOrderOffersComponent } from './medication-order-offers.component';

describe('MedicationOrderOffersComponent', () => {
  let component: MedicationOrderOffersComponent;
  let fixture: ComponentFixture<MedicationOrderOffersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MedicationOrderOffersComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MedicationOrderOffersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
