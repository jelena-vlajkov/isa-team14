import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SupplierAllOffersComponent } from './supplier-all-offers.component';

describe('SupplierAllOffersComponent', () => {
  let component: SupplierAllOffersComponent;
  let fixture: ComponentFixture<SupplierAllOffersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SupplierAllOffersComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SupplierAllOffersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
