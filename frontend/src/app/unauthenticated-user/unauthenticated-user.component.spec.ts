import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UnauthenticatedUserComponent } from './unauthenticated-user.component';

describe('UnauthenticatedUserComponent', () => {
  let component: UnauthenticatedUserComponent;
  let fixture: ComponentFixture<UnauthenticatedUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UnauthenticatedUserComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UnauthenticatedUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
