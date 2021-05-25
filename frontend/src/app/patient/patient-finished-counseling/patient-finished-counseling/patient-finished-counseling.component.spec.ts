import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientFinishedCounselingComponent } from './patient-finished-counseling.component';

describe('PatientFinishedCounselingComponent', () => {
  let component: PatientFinishedCounselingComponent;
  let fixture: ComponentFixture<PatientFinishedCounselingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatientFinishedCounselingComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientFinishedCounselingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
