import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PharmacyProfileMockComponent } from './pharmacy-profile-mock.component';

describe('PharmacyProfileMockComponent', () => {
  let component: PharmacyProfileMockComponent;
  let fixture: ComponentFixture<PharmacyProfileMockComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PharmacyProfileMockComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PharmacyProfileMockComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
