package com.github.inggl.erp.notification.repositories;

import com.github.inggl.erp.notification.entities.Archive;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
public class ArchiveRepository implements PanacheRepositoryBase<Archive, UUID> {
}
