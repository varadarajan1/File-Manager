import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpRequest } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { FormBuilder, FormGroup } from '@angular/forms';
import { BlobInfo, BlobInfoResponse, BlobResponse } from './model/blobinforesponse';
@Injectable({
  providedIn: 'root'
})
export class FileService {
  serverURl: string = "/api";

  constructor(private httpClient: HttpClient, private formBuilder: FormBuilder) { }
  uploadFile(file: File): Observable<any> {
    const formData = new FormData();
    formData.append("file", file);
    const uploadReq = new HttpRequest('POST', this.serverURl + "/upload", formData, {
      reportProgress: true
    });
    return this.httpClient.request(uploadReq);
  }

  getAllFileDetails(): Observable<BlobInfoResponse> {
    const formData = new FormData();
    return this.httpClient.get<BlobInfoResponse>(this.serverURl + "/all");
  }

  downloadFile(name:string):Observable<any>{
    return this.httpClient.get<any>(this.serverURl+"/"+name,{responseType: 'blob' as 'json'});
  }

  deleteFile(name: String): Observable<BlobResponse> {
    return this.httpClient.delete<BlobResponse>(this.serverURl + "/" + name);
  }
}