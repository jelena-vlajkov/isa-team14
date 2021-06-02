import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientScheduleCounselingComponent } from './patient-schedule-counseling.component';

describe('PatientScheduleCounselingComponent', () => {
  let component: PatientScheduleCounselingComponent;
  let fixture: ComponentFixture<PatientScheduleCounselingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatientScheduleCounselingComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientScheduleCounselingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
