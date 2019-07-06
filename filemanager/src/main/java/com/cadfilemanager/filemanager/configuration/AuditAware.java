package com.cadfilemanager.filemanager.configuration;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return  Optional.of("File-Manager-service");
    }
}
