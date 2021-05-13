import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditAllergiesComponent } from './edit-allergies.component';

describe('EditAllergiesComponent', () => {
  let component: EditAllergiesComponent;
  let fixture: ComponentFixture<EditAllergiesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditAllergiesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditAllergiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
