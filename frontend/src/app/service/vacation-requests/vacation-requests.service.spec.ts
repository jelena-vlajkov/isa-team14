import { TestBed } from '@angular/core/testing';

import { VacationRequestsService } from './vacation-requests.service';

describe('VacationRequestsService', () => {
  let service: VacationRequestsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VacationRequestsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
