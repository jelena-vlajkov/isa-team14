import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AutocompleteGeoComponent } from './autocomplete-geo.component';

describe('AutocompleteGeoComponent', () => {
  let component: AutocompleteGeoComponent;
  let fixture: ComponentFixture<AutocompleteGeoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AutocompleteGeoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AutocompleteGeoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
