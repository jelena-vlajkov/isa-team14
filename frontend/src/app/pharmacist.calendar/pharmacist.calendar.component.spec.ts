import { ComponentFixture, TestBed } from '@angular/core/testing';


import { PharmacistCalendarComponent } from './pharmacist.calendar.component';

describe('PharmacistComponent', () => {
  let component: PharmacistCalendarComponent;
  let fixture: ComponentFixture<PharmacistCalendarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PharmacistCalendarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PharmacistCalendarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
