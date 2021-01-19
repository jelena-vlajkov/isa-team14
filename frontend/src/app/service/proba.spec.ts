import { TestBed } from '@angular/core/testing';

import { Proba } from './proba';

describe('Proba', () => {
  let service: Proba;
  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Proba);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
