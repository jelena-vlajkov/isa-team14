import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientNewGradeComponent } from './patient-new-grade.component';

describe('PatientNewGradeComponent', () => {
  let component: PatientNewGradeComponent;
  let fixture: ComponentFixture<PatientNewGradeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatientNewGradeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientNewGradeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
