import { TestBed } from '@angular/core/testing';

import { PharmacyAdminService } from './pharmacy-admin.service';

describe('PharmacyAdminService', () => {
  let service: PharmacyAdminService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PharmacyAdminService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
