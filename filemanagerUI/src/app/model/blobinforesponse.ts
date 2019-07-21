export class BlobInfoResponse {
    result: string;
    errorMessage: string;
    blobs: BlobInfo[];
}
export class BlobInfo{
   blobName: string;
   size: string;
   updatedDateTime: string;
}

export class BlobResponse{
    result: string;
    errorMessage: string;
}