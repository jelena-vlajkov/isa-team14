import { TestBed } from '@angular/core/testing';

import { DrugInquiryService } from './drug-inquiry.service';

describe('DrugInquiryService', () => {
  let service: DrugInquiryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DrugInquiryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
