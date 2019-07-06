package com.cadfilemanager.filemanager.repository;

import com.cadfilemanager.filemanager.entity.FileManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface SqlRepository extends JpaRepository<FileManager,String> {
}
