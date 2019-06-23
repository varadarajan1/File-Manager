package com.cadfilemanager.filemanager.advice;

import com.cadfilemanager.filemanager.models.FileUploadResponse;
import com.microsoft.azure.storage.StorageException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({IOException.class, URISyntaxException.class})
    public ResponseEntity<FileUploadResponse> handleRunTimeException(Exception e) {
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(FileUploadResponse.builder().result("Failure").errorMessage(e.getMessage()).build());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({StorageException.class})
    public ResponseEntity<FileUploadResponse> handleStorageException(Exception e) {
        return ResponseEntity.status(BAD_REQUEST).body(FileUploadResponse.builder().result("Failure").errorMessage(e.getMessage()).build());
    }
}
