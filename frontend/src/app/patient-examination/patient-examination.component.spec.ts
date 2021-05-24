import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientExaminationComoponent } from './patient-examination.component';

describe('PatientExaminationComoponent', () => {
  let component: PatientExaminationComoponent;
  let fixture: ComponentFixture<PatientExaminationComoponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatientExaminationComoponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientExaminationComoponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
