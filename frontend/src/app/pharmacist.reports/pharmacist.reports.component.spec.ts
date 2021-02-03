import { ComponentFixture, TestBed } from '@angular/core/testing';


import { PharmacistReportsComponent } from './pharmacist.reports.component';

describe('PharmacistComponent', () => {
  let component: PharmacistReportsComponent;
  let fixture: ComponentFixture<PharmacistReportsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PharmacistReportsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PharmacistReportsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
