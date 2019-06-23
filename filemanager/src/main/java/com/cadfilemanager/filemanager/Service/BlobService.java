package com.cadfilemanager.filemanager.Service;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BlobService {

    @Autowired
    CloudBlobContainer cloudBlobContainer;

    public void uploadBlob(MultipartFile file) throws URISyntaxException, StorageException, IOException {
        cloudBlobContainer.getBlockBlobReference(file.getOriginalFilename()).upload(file.getInputStream(), file.getSize());
    }

    public List<String> getAllBlobNames() {
        return StreamSupport.stream(cloudBlobContainer.listBlobs().spliterator(), false)
                .map(x -> ((CloudBlockBlob) x).getName()).collect(Collectors.toList());
    }

    public void getBlob(OutputStream outputStream, String blobName) throws URISyntaxException, StorageException {
        cloudBlobContainer.getBlockBlobReference(blobName).download(outputStream);
    }

    public void deleteBlob(String name) throws URISyntaxException, StorageException {
        cloudBlobContainer.getBlockBlobReference(name).deleteIfExists();
    }
}
