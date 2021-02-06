import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientComplainComponent } from './patient-complain.component';

describe('PatientComplainComponent', () => {
  let component: PatientComplainComponent;
  let fixture: ComponentFixture<PatientComplainComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatientComplainComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientComplainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
