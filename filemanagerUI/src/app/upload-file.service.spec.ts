import { TestBed } from '@angular/core/testing';

import { FileService } from './upload-file.service';

describe('UploadFileService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FileService = TestBed.get(FileService);
    expect(service).toBeTruthy();
  });
});
