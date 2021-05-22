import { ComponentFixture, TestBed } from '@angular/core/testing';


import { UsersPreview } from './users.preview.component';

describe('UsersPreview', () => {
  let component: UsersPreview;
  let fixture: ComponentFixture<UsersPreview>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UsersPreview ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UsersPreview);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
