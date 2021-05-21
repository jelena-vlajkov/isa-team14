import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientIssuedEDrugsComponent } from './patient-issued-e-drugs.component';

describe('PatientIssuedEDrugsComponent', () => {
  let component: PatientIssuedEDrugsComponent;
  let fixture: ComponentFixture<PatientIssuedEDrugsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatientIssuedEDrugsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientIssuedEDrugsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
