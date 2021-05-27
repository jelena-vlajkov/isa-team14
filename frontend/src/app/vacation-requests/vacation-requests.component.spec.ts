import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VacationRequestsComponent } from './vacation-requests.component';

describe('VacationRequestsComponent', () => {
  let component: VacationRequestsComponent;
  let fixture: ComponentFixture<VacationRequestsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VacationRequestsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VacationRequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
