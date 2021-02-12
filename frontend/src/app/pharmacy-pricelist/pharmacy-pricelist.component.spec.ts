import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PharmacyPricelistComponent } from './pharmacy-pricelist.component';

describe('PharmacyPricelistComponent', () => {
  let component: PharmacyPricelistComponent;
  let fixture: ComponentFixture<PharmacyPricelistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PharmacyPricelistComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PharmacyPricelistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
