import { TestBed } from '@angular/core/testing';

import { PharmacyStorageService } from './pharmacy-storage.service';

describe('PharmacyStorageService', () => {
  let service: PharmacyStorageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PharmacyStorageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
