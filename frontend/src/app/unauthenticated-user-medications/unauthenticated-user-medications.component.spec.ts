import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UnauthenticatedUserMedicationsComponent } from './unauthenticated-user-medications.component';

describe('UnauthenticatedUserMedicationsComponent', () => {
  let component: UnauthenticatedUserMedicationsComponent;
  let fixture: ComponentFixture<UnauthenticatedUserMedicationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UnauthenticatedUserMedicationsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UnauthenticatedUserMedicationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
