package com.cadfilemanager.filemanager.controller;

import com.cadfilemanager.filemanager.Service.BlobService;
import com.cadfilemanager.filemanager.models.BlobNameResponse;
import com.cadfilemanager.filemanager.models.FileUploadResponse;
import com.microsoft.azure.storage.StorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class FileController {

    @Autowired
    private BlobService blobService;

    @PostMapping("/upload")
    public FileUploadResponse uploadFile(@RequestParam("file") MultipartFile file) throws StorageException, IOException, URISyntaxException {
        blobService.uploadBlob(file);
        return FileUploadResponse.builder().result("Success").build();
    }

    @GetMapping("/all")
    public BlobNameResponse getAllBlobNames() {
        List<String> blobNames = blobService.getAllBlobNames();
        BlobNameResponse response = BlobNameResponse.BlobNameBuilder().blobNames(blobNames).build();
        response.setResult("Success");
        return response;
    }

    @ResponseBody
    @GetMapping("/{name}")
    public ResponseEntity<Resource> getBlobByName(@PathVariable String name) throws IOException, URISyntaxException, StorageException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        blobService.getBlob(outputStream, name);
        ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());
        outputStream.close();
        return ResponseEntity.ok().contentLength(resource.contentLength())
                .header("Content-Disposition", "attachment; filename=" + name)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }

    @DeleteMapping("/{name}")
    public FileUploadResponse deleteBlobByName(@PathVariable String name) throws URISyntaxException, StorageException {
        blobService.deleteBlob(name);
        return FileUploadResponse.builder().result("Success").build();
    }
}