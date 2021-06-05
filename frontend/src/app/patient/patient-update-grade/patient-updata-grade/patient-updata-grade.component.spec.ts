import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientUpdataGradeComponent } from './patient-updata-grade.component';

describe('PatientUpdataGradeComponent', () => {
  let component: PatientUpdataGradeComponent;
  let fixture: ComponentFixture<PatientUpdataGradeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatientUpdataGradeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientUpdataGradeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
