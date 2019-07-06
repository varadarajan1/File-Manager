package com.cadfilemanager.filemanager.configuration;

import com.cadfilemanager.filemanager.service.AzureStorageBlobService;
import com.cadfilemanager.filemanager.service.BlobService;
import com.cadfilemanager.filemanager.service.SqlService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class BlobConfigration {

    @Bean
    @ConditionalOnProperty(value = "blob.enabled", havingValue = "false", matchIfMissing = true)
    @Primary
    public BlobService getSqlBlobService(){
        return new SqlService();
    }

    @Bean
    @ConditionalOnProperty(value = "blob.enabled", havingValue = "true", matchIfMissing = false)
    public BlobService getAzureStorageBlobService(){
        return new AzureStorageBlobService();
    }
}
