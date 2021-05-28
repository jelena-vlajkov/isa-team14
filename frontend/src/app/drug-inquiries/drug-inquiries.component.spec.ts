import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DrugInquiriesComponent } from './drug-inquiries.component';

describe('DrugInquiriesComponent', () => {
  let component: DrugInquiriesComponent;
  let fixture: ComponentFixture<DrugInquiriesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DrugInquiriesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DrugInquiriesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
