import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PharmacistListAndFilterComponent } from './pharmacist-list-and-filter.component';

describe('PharmacistListAndFilterComponent', () => {
  let component: PharmacistListAndFilterComponent;
  let fixture: ComponentFixture<PharmacistListAndFilterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PharmacistListAndFilterComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PharmacistListAndFilterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
