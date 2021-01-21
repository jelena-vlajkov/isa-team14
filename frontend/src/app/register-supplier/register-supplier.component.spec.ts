import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterSupplierComponent } from './register-supplier.component';

describe('RegisterSupplierComponent', () => {
  let component: RegisterSupplierComponent;
  let fixture: ComponentFixture<RegisterSupplierComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegisterSupplierComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterSupplierComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
