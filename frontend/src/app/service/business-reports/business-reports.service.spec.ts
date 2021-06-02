import { TestBed } from '@angular/core/testing';

import { BusinessReportsService } from './business-reports.service';

describe('BusinessReportsService', () => {
  let service: BusinessReportsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BusinessReportsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
