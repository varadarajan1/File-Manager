package com.cadfilemanager.filemanager.service;

import com.cadfilemanager.filemanager.models.BlobInfo;
import com.cadfilemanager.filemanager.utils.FileManagerUtil;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AzureStorageBlobService implements BlobService {

    @Autowired(required = false)
    CloudBlobContainer cloudBlobContainer;

    public void uploadBlob(MultipartFile file) throws Exception {
        cloudBlobContainer.getBlockBlobReference(file.getOriginalFilename()).upload(file.getInputStream(), file.getSize());
    }

    public List<BlobInfo> getAllBlobNames() {
        return StreamSupport.stream(cloudBlobContainer.listBlobs().spliterator(), false)
                .map(x -> getBlobInfo((CloudBlockBlob)x)).collect(Collectors.toList());
    }

    public void getBlob(OutputStream outputStream, String blobName) throws Exception{
        cloudBlobContainer.getBlockBlobReference(blobName).download(outputStream);
    }

    public void deleteBlob(String name) throws Exception{
        cloudBlobContainer.getBlockBlobReference(name).deleteIfExists();
    }

    private BlobInfo getBlobInfo(CloudBlockBlob blockBlob){
        return BlobInfo.builder().blobName(blockBlob.getName())
                .size(FileManagerUtil.convertSizeFromBytesToKiloBytes(blockBlob.getProperties().getLength()))
                .updatedDateTime(blockBlob.getProperties().getLastModified().toString())
                .build();
    }
}
