import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SupplierOffersComponent } from './supplier-offers.component';

describe('SupplierOffersComponent', () => {
  let component: SupplierOffersComponent;
  let fixture: ComponentFixture<SupplierOffersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SupplierOffersComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SupplierOffersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
