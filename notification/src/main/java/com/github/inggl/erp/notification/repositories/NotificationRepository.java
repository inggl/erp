package com.github.inggl.erp.notification.repositories;

import com.github.inggl.erp.notification.entities.Notification;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NotificationRepository implements ReactivePanacheMongoRepository<Notification> {
}
