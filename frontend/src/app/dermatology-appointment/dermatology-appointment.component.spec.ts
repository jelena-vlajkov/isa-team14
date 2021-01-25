import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DermatologyAppointmentComponent } from './dermatology-appointment.component';

describe('DermatologyAppointmentComponent', () => {
  let component: DermatologyAppointmentComponent;
  let fixture: ComponentFixture<DermatologyAppointmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DermatologyAppointmentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DermatologyAppointmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
