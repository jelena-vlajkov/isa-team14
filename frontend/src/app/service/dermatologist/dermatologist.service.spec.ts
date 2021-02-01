import { TestBed } from '@angular/core/testing';

import { DermatologistService } from './dermatologist.service';

describe('DermatologistService', () => {
  let service: DermatologistService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DermatologistService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
