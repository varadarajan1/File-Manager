import { Component, OnInit, Injectable, Inject } from '@angular/core';
import { FileService } from '../upload-file.service';
import { BlobInfoResponse, BlobInfo } from '../model/blobinforesponse';
import { Subscriber } from 'rxjs';
import { HttpEventType } from '@angular/common/http';

export interface PeriodicElement {
  name: string;
  position: number;
  weight: number;
  symbol: string;
}

@Injectable({
  providedIn: 'root'
})
@Component({
  selector: 'app-file-view',
  templateUrl: './file-view.component.html',
  styleUrls: ['./file-view.component.css']
})
export class FileViewComponent implements OnInit {
  blobs: BlobInfo[];
  error: any;
  collectionSize: Number;
  config: any;
  fileDownloadInProgress: boolean = false;
  fileDownloadStatus: String;
  ngOnInit(): void {
    this.fileService.getAllFileDetails()
      .subscribe(
        (value) => {
          this.blobs = value.blobs;
          this.collectionSize = value.blobs.length
          this.config = {
            itemsPerPage: 5,
            currentPage: 1,
            totalItems: this.collectionSize
          };
        },
        (error) => { this.blobs = []; this.error = error },
        () => { console.log("Successfully retrieved blob details") }
      )
  }
  constructor(private fileService: FileService) {
  }

  onSelect(blob: BlobInfo) {
    this.fileService.downloadFile(blob.blobName).subscribe(data => this.downloadFile(data, blob.blobName)),//console.log(data),
      error => console.log('Error downloading the file.'),
      () => console.info('OK');
  }

  downloadFile(data: any, blobName: string): void {
    let link = document.createElement('a');
    link.setAttribute('type', 'hidden');
    const url = window.URL.createObjectURL(data);
    link.href = url;
    link.download = blobName;
    link.click();
    window.URL.revokeObjectURL(url);
  }

  deleteFieldValue(blob: BlobInfo) {
    this.fileService.deleteFile(blob.blobName).subscribe(res => {
      if (res.errorMessage == null) {
        console.log("successfully deleted file");
        window.location.reload();
      } else {
        console.log("Error" + res.errorMessage);
      }
    },
      error => console.log('Error downloading the file.'),
      () => console.info('OK')
    )
  }

  pageChanged(event) {
    this.config.currentPage = event;
  }
}
