package com.github.inggl.erp.notification.services;

import com.github.inggl.erp.notification.entities.Archive;

import java.util.List;
import java.util.UUID;

public interface ArchiveService {
    List<Archive> findAll();
    Archive findById(UUID archiveId);
    void create(Archive archive);
    void update(Archive archive);
    void delete(UUID archiveId);
}
