package com.github.inggl.erp.notification.services;

import com.github.inggl.erp.notification.entities.Notification;
import com.github.inggl.erp.notification.repositories.NotificationRepository;
import com.google.common.base.Preconditions;
import com.mongodb.client.model.changestream.FullDocument;
import io.quarkus.mongodb.ChangeStreamOptions;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

@ApplicationScoped
public class NotificationServiceImpl implements NotificationService {
    @Inject Logger log;

    @Inject NotificationRepository notificationRepository;

    @Override
    public Uni<List<Notification>> findAll() {
        return notificationRepository.findAll().list();
    }

    @Override
    public Multi<Notification> streamAll() {
        log.info("Notification streaming");

        ChangeStreamOptions options = new ChangeStreamOptions().fullDocument(FullDocument.UPDATE_LOOKUP);

        return notificationRepository.mongoCollection()
                .watch(Notification.class, options)
                .log()
                .flatMap(notificationChangeStreamDocument -> Multi.createFrom().item(notificationChangeStreamDocument.getFullDocument()));
    }

    @Override
    public Uni<Notification> save(Notification notification) {
        Preconditions.checkArgument(notification != null, "Notification is missing");
        Preconditions.checkState(StringUtils.isNotBlank(notification.title), "Notification title is missing");
        Preconditions.checkState(StringUtils.isNotBlank(notification.message), "Notification title is message");

        log.info("Persisting notification");
        return notificationRepository.persist(notification);
    }

    @Override
    public Uni<Void> delete(Notification notification) {
        Preconditions.checkArgument(notification != null, "Notification is missing");
        Preconditions.checkArgument(notification.id != null, "Invalid notification ID");

        return notificationRepository.delete(notification);
    }
}
