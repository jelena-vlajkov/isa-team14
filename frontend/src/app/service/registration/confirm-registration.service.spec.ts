import { TestBed } from '@angular/core/testing';

import { ConfirmRegistration } from './confirm-registration.service';

describe('ConfirmRegistration', () => {
  let service: ConfirmRegistration;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ConfirmRegistration);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
