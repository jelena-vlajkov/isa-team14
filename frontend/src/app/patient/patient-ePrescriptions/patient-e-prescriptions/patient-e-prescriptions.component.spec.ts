import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientEPrescriptionsComponent } from './patient-e-prescriptions.component';

describe('PatientEPrescriptionsComponent', () => {
  let component: PatientEPrescriptionsComponent;
  let fixture: ComponentFixture<PatientEPrescriptionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatientEPrescriptionsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientEPrescriptionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
