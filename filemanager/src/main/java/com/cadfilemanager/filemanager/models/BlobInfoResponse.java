package com.cadfilemanager.filemanager.models;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "BlobNameBuilder")
@EqualsAndHashCode(callSuper = false)
public class BlobInfoResponse extends FileUploadResponse {
    private List<BlobInfo> blobs;
}