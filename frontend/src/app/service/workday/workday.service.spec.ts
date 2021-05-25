import { TestBed } from '@angular/core/testing';

import { WorkdayService } from './workday.service';

describe('WorkdayService', () => {
  let service: WorkdayService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WorkdayService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
