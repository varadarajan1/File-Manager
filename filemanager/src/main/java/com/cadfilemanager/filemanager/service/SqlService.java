package com.cadfilemanager.filemanager.service;

import com.cadfilemanager.filemanager.models.BlobInfo;
import com.cadfilemanager.filemanager.repository.SqlRepository;
import com.cadfilemanager.filemanager.entity.FileManager;
import com.cadfilemanager.filemanager.utils.FileManagerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SqlService implements BlobService {

    @Autowired
    private SqlRepository repository;

    @Override
    public void uploadBlob(MultipartFile file) throws Exception {
        FileManager fileManager = FileManager
                .builder()
                .fileName(file.getOriginalFilename())
                .blob(file.getBytes())
                .size(FileManagerUtil.convertSizeFromBytesToKiloBytes(file.getSize()))
                .build();
        repository.save(fileManager);
    }

    @Override
    public List<BlobInfo> getAllBlobNames() {
        List<FileManager> files = Optional.of(repository.findAll()).orElse(new ArrayList<>());
        return files.stream().map(x->new BlobInfo(x.getFileName(),x.getSize(),x.getUpdateDateTime().toString())).collect(Collectors.toList());
    }

    @Override
    public void getBlob(OutputStream outputStream, String blobName) throws Exception {
       Optional<FileManager> file  = Optional.ofNullable(repository.findById(blobName)).orElseThrow(()-> new IllegalArgumentException("No record found"));
       outputStream.write(file.get().getBlob());
    }

    @Override
    public void deleteBlob(String name) throws Exception {
        repository.deleteById(name);
    }
}
