package com.github.inggl.erp.notification.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "ARCHIVE")
public class Archive extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ARCHIVE_ID")
    public UUID id;

    @Column(name = "TITLE", length = 50)
    public String title;

    @Column(name = "MESSAGE", length = 100)
    public String message;

    @Column(name = "CREATED_BY", updatable = false)
    private String createdBy;

    @Column(name = "CREATED_DATE", updatable = false)
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(name = "LAST_MODIFIED_BY")
    private String lastModifiedBy;

    @Column(name = "LAST_MODIFIED_DATE")
    private LocalDateTime lastModifiedDate = LocalDateTime.now();
}
