import { ComponentFixture, TestBed } from '@angular/core/testing';


import { PharmacistAddReportComponent } from './pharmacist.add-report.component';

describe('PharmacistComponent', () => {
  let component: PharmacistAddReportComponent;
  let fixture: ComponentFixture<PharmacistAddReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PharmacistAddReportComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PharmacistAddReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
