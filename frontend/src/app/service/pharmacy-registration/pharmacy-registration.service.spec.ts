import { TestBed } from '@angular/core/testing';

import { PharmacyRegistrationService } from './pharmacy-registration.service';

describe('PharmacyRegistrationService', () => {
  let service: PharmacyRegistrationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PharmacyRegistrationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
