import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PharmacyAdminProfileComponent } from './pharmacy-admin-profile.component';

describe('PharmacyAdminProfileComponent', () => {
  let component: PharmacyAdminProfileComponent;
  let fixture: ComponentFixture<PharmacyAdminProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PharmacyAdminProfileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PharmacyAdminProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
