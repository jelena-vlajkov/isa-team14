import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DermatologistsInPharmacyComponent } from './dermatologists-in-pharmacy.component';

describe('DermatologistsInPharmacyComponent', () => {
  let component: DermatologistsInPharmacyComponent;
  let fixture: ComponentFixture<DermatologistsInPharmacyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DermatologistsInPharmacyComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DermatologistsInPharmacyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
