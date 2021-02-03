import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UnauthenticatedUserPharmaciesComponent } from './unauthenticated-user-pharmacies.component';

describe('UnauthenticatedUserPharmaciesComponent', () => {
  let component: UnauthenticatedUserPharmaciesComponent;
  let fixture: ComponentFixture<UnauthenticatedUserPharmaciesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UnauthenticatedUserPharmaciesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UnauthenticatedUserPharmaciesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
