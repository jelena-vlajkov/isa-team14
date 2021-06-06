import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientScheduleExaminationComponent } from './patient-schedule-examination.component';

describe('PatientScheduleExaminationComponent', () => {
  let component: PatientScheduleExaminationComponent;
  let fixture: ComponentFixture<PatientScheduleExaminationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatientScheduleExaminationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientScheduleExaminationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
