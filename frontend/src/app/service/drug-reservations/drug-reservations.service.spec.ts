import { TestBed } from '@angular/core/testing';

import { DrugReservationsService } from './drug-reservations.service';

describe('DrugReservationsService', () => {
  let service: DrugReservationsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DrugReservationsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
