import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientReservedDrugsComponent } from './patient-reserved-drugs.component';

describe('PatientReservedDrugsComponent', () => {
  let component: PatientReservedDrugsComponent;
  let fixture: ComponentFixture<PatientReservedDrugsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatientReservedDrugsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientReservedDrugsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
