import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientScheduledAppointmentsComponent } from './patient-scheduled-appointments.component';

describe('PatientScheduledAppointmentsComponent', () => {
  let component: PatientScheduledAppointmentsComponent;
  let fixture: ComponentFixture<PatientScheduledAppointmentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatientScheduledAppointmentsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientScheduledAppointmentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
