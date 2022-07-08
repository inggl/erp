package com.github.inggl.erp.notification.rest;

import com.github.inggl.erp.notification.entities.Notification;
import com.github.inggl.erp.notification.services.NotificationService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.RestStreamElementType;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/notifications")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class NotificationResource {
    @Inject
    Logger log;
    @Inject
    NotificationService notificationService;

    @GET
    @Path("/")
    public Uni<List<Notification>> findAll()
    {
        return notificationService.findAll();
    }

    @GET
    @Path("/stream")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @RestStreamElementType(MediaType.APPLICATION_JSON)
    public Multi<Notification> streamAll()
    {
        return notificationService.streamAll();
    }

    @POST
    public Uni<Notification> save(Notification notification)
    {
        return notificationService.save(notification);
    }

    @DELETE
    public Uni<Void> delete(Notification notification)
    {
        return notificationService.delete(notification);
    }
}
