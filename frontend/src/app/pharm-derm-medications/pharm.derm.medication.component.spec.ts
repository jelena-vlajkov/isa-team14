import { ComponentFixture, TestBed } from '@angular/core/testing';


import { PharmDermMedicationsComponent } from './pharm.derm.medication.component';

describe('PharmDermMedicationsComponent', () => {
  let component: PharmDermMedicationsComponent;
  let fixture: ComponentFixture<PharmDermMedicationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PharmDermMedicationsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PharmDermMedicationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
