import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DefineLoyaltyComponent } from './define-loyalty.component';

describe('DefineLoyaltyComponent', () => {
  let component: DefineLoyaltyComponent;
  let fixture: ComponentFixture<DefineLoyaltyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DefineLoyaltyComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DefineLoyaltyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
