import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterDermatologistComponent } from './register-dermatologist.component';

describe('RegisterDermatologistComponent', () => {
  let component: RegisterDermatologistComponent;
  let fixture: ComponentFixture<RegisterDermatologistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegisterDermatologistComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterDermatologistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
