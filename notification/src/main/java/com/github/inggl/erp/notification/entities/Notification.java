package com.github.inggl.erp.notification.entities;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;

import java.io.Serializable;
import java.time.LocalDateTime;

@MongoEntity(collection = "notification")
public class Notification extends ReactivePanacheMongoEntity implements Serializable {
    public String title;
    public String message;
    public LocalDateTime createdAt = LocalDateTime.now();
}
