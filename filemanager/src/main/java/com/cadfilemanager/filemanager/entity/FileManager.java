package com.cadfilemanager.filemanager.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EnableJpaAuditing(auditorAwareRef = "AuditAware")
@EntityListeners(AuditingEntityListener.class)
public class FileManager {

    @Id
    private String fileName;
    @Lob
    private byte[] blob;
    private String size;
    @UpdateTimestamp
    private Timestamp updateDateTime;
    @CreationTimestamp
    private Timestamp createdDateTime;
}
