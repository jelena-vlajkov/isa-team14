import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientExaminationSchedulingComponent } from './patient-examination-scheduling.component';

describe('PatientExaminationSchedulingComponent', () => {
  let component: PatientExaminationSchedulingComponent;
  let fixture: ComponentFixture<PatientExaminationSchedulingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatientExaminationSchedulingComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientExaminationSchedulingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
