package com.github.inggl.erp.notification.services;

import com.github.inggl.erp.notification.entities.Notification;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import java.util.List;

public interface NotificationService {
    Uni<List<Notification>> findAll();
    Multi<Notification> streamAll();
    Uni<Notification> save(Notification notification);
    Uni<Void> delete(Notification notification);
}
