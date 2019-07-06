package com.cadfilemanager.filemanager.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlobInfo {
 private String blobName;
 private String size;
 private String updatedDateTime;
}
