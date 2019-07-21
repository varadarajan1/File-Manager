import { Component, OnInit, Inject, Injectable, ViewChild, ElementRef } from '@angular/core';
import { FileService } from '../upload-file.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpEventType } from '@angular/common/http';
import { BlobInfoResponse } from '../model/blobinforesponse';

@Component({
  selector: 'app-upload-file',
  templateUrl: './upload-file.component.html',
  styleUrls: ['./upload-file.component.css']
})

@Injectable({
  providedIn: 'root'
})
export class UploadFileComponent implements OnInit {
  fileToUpload: File = null;
  fileUploadStatus: String = null;
  public formImport: FormGroup;
  fileUploadInProgress: boolean = false;

  @ViewChild('labelImport')
  labelImport: ElementRef;

  constructor(private uploadfileService: FileService) {
    this.formImport = new FormGroup({
      importFile: new FormControl('', Validators.required)
    });
  }

  ngOnInit() {
    this.fileUploadStatus = null
  }

  onFileChange(files: FileList) {
    this.labelImport.nativeElement.innerText = Array.from(files)
      .map(f => f.name)
      .join(', ');
    this.fileToUpload = files.item(0);
  }

  import(): void {
    console.log('import ' + this.fileToUpload.name);
    this.uploadfileService.uploadFile(this.fileToUpload).subscribe((status) => {
        this.handleUploadEvent(status);
    }, (reason: any) => { console.log(reason); })
  }

  handleUploadEvent(event) {
    if (event.type === HttpEventType.UploadProgress) {
      this.fileUploadInProgress = true;
      let uploaded = Math.round(100 * event.loaded / event.total)
      this.fileUploadStatus = "Uploaded "+ +"% of the file";
      if(uploaded==100){
        this.fileUploadStatus="Waiting for response from the server";
      }
    }
    if (event.type === HttpEventType.Response) { 
      this.fileUploadInProgress = false
      window.location.reload();
      let response:BlobInfoResponse = event.body;
      if (response.result=="Success")
        this.fileUploadStatus = "Successfully uploaded file";
      else
        this.fileUploadStatus = "File upload failed";
      console.log(response);
    }
  }
}
