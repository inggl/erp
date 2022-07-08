package com.github.inggl.erp.notification.services;

import com.github.inggl.erp.notification.entities.Archive;
import com.github.inggl.erp.notification.repositories.ArchiveRepository;
import com.google.common.base.Preconditions;
import io.quarkus.panache.common.Parameters;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
@Transactional
public class ArchiveServiceImpl implements ArchiveService {
    @Inject
    ArchiveRepository archiveRepository;

    @Override
    public List<Archive> findAll()
    {
        return archiveRepository.listAll();
    }

    @Override
    public Archive findById(UUID archiveId)
    {
        return archiveRepository.findById(archiveId);
    }

    @Override
    public void create(Archive archive)
    {
        archiveRepository.persist(archive);
    }

    @Override
    public void update(Archive archive)
    {
        Preconditions.checkState(StringUtils.isNotBlank(archive.title), "Archive title is missing");
        Preconditions.checkState(StringUtils.isNotBlank(archive.message), "Archive message not found");

        archiveRepository.update("title = :title and message = :message",
                                 Parameters.with("title", archive.title).and("message", archive.message));
    }

    @Override
    public void delete(UUID archiveId)
    {
        archiveRepository.deleteById(archiveId);
    }
}
