import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientSubscriptionsComponent } from './patient-subscriptions.component';

describe('PatientSubscriptionsComponent', () => {
  let component: PatientSubscriptionsComponent;
  let fixture: ComponentFixture<PatientSubscriptionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatientSubscriptionsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientSubscriptionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
