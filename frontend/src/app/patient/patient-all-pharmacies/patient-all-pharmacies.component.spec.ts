import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientAllPharmaciesComponent } from './patient-all-pharmacies.component';

describe('PatientAllPharmaciesComponent', () => {
  let component: PatientAllPharmaciesComponent;
  let fixture: ComponentFixture<PatientAllPharmaciesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatientAllPharmaciesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientAllPharmaciesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
