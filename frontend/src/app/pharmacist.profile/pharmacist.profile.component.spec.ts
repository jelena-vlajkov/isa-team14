import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PharmacistProfileComponent } from './pharmacist.profile.component';

describe('PharmacistProfileComponent', () => {
  let component: PharmacistProfileComponent;
  let fixture: ComponentFixture<PharmacistProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PharmacistProfileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PharmacistProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
