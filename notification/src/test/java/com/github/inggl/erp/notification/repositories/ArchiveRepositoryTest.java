package com.github.inggl.erp.notification.repositories;

import io.quarkus.arc.Priority;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

@Priority(1)
@Alternative
@ApplicationScoped
class ArchiveRepositoryTest extends ArchiveRepository {
    @PostConstruct
    public void init() {

    }
}