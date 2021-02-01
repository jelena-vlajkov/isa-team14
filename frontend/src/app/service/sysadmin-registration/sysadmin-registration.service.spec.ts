import { TestBed } from '@angular/core/testing';

import { SysadminRegistrationService } from './sysadmin-registration.service';

describe('SysadminRegistrationService', () => {
  let service: SysadminRegistrationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SysadminRegistrationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
