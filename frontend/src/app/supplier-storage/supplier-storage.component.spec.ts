import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SupplierStorageComponent } from './supplier-storage.component';

describe('SupplierStorageComponent', () => {
  let component: SupplierStorageComponent;
  let fixture: ComponentFixture<SupplierStorageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SupplierStorageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SupplierStorageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
