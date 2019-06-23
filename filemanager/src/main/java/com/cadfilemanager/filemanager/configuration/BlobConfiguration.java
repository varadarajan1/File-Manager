package com.cadfilemanager.filemanager.configuration;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

@Configuration
public class BlobConfiguration {

//    @Value("${storage.accountKey}")
//    private String accountKey;
//
//    @Value("${storage.accountName}")
//    private String accountName;
//
//    @Value("${storage.serviceURL}")
//    private String serviceURL;
//
//    @Value("${storage.containerName}")
//    private String containerName;

    @Value("${storage.connectionString}")
    private String connectionString;

    @Value("${storage.containerName}")
    private String containerName;

    @Bean
    @Primary
    CloudStorageAccount getCloudStorageAccount() throws URISyntaxException, InvalidKeyException {
        return CloudStorageAccount.parse(connectionString);
    }

    @Bean
    public CloudBlobContainer getContainer(CloudStorageAccount cloudStorageAccount) throws URISyntaxException, StorageException {
        CloudBlobClient cloudBlobClient = cloudStorageAccount.createCloudBlobClient();
        return cloudBlobClient.getContainerReference(this.containerName);
    }
}
