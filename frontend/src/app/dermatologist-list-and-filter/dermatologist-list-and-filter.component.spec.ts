import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DermatologistListAndFilterComponent } from './dermatologist-list-and-filter.component';

describe('DermatologistListAndFilterComponent', () => {
  let component: DermatologistListAndFilterComponent;
  let fixture: ComponentFixture<DermatologistListAndFilterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DermatologistListAndFilterComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DermatologistListAndFilterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
