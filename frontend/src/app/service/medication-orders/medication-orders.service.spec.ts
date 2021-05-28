import { TestBed } from '@angular/core/testing';

import { MedicationOrdersService } from './medication-orders.service';

describe('MedicationOrdersService', () => {
  let service: MedicationOrdersService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MedicationOrdersService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
