import { TestBed, inject } from '@angular/core/testing';

import { UlploadService } from './ulpload.service';

describe('UlploadService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [UlploadService]
    });
  });

  it('should ...', inject([UlploadService], (service: UlploadService) => {
    expect(service).toBeTruthy();
  }));
});
