import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientDrugReservationComponent } from './patient-drug-reservation.component';

describe('PatientDrugReservationComponent', () => {
  let component: PatientDrugReservationComponent;
  let fixture: ComponentFixture<PatientDrugReservationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatientDrugReservationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientDrugReservationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
