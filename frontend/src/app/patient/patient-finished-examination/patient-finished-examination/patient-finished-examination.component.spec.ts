import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientFinishedExaminationComponent } from './patient-finished-examination.component';

describe('PatientFinishedExaminationComponent', () => {
  let component: PatientFinishedExaminationComponent;
  let fixture: ComponentFixture<PatientFinishedExaminationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatientFinishedExaminationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientFinishedExaminationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
