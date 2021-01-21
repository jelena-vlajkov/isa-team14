import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterPharmacyComponent } from './register-pharmacy.component';

describe('RegisterPharmacyComponent', () => {
  let component: RegisterPharmacyComponent;
  let fixture: ComponentFixture<RegisterPharmacyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegisterPharmacyComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterPharmacyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
