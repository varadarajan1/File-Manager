package com.cadfilemanager.filemanager.service;

import com.cadfilemanager.filemanager.models.BlobInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
import java.util.List;

public interface BlobService {

    void uploadBlob(MultipartFile file) throws Exception;
    List<BlobInfo> getAllBlobNames();
    void getBlob(OutputStream outputStream, String blobName) throws Exception;
    void deleteBlob(String name) throws Exception;
}
