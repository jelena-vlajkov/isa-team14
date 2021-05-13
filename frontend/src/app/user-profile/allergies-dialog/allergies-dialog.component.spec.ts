import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllergiesDialogComponent } from './allergies-dialog.component';

describe('AllergiesDialogComponent', () => {
  let component: AllergiesDialogComponent;
  let fixture: ComponentFixture<AllergiesDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllergiesDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AllergiesDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
