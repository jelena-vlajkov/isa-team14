import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterPharmacyadminComponent } from './register-pharmacyadmin.component';

describe('RegisterPharmacyadminComponent', () => {
  let component: RegisterPharmacyadminComponent;
  let fixture: ComponentFixture<RegisterPharmacyadminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegisterPharmacyadminComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterPharmacyadminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
