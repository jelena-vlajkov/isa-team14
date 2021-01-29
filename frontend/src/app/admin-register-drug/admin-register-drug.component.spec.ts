import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminRegisterDrugComponent } from './admin-register-drug.component';

describe('AdminRegisterDrugComponent', () => {
  let component: AdminRegisterDrugComponent;
  let fixture: ComponentFixture<AdminRegisterDrugComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminRegisterDrugComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminRegisterDrugComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
