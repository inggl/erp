package com.github.inggl.erp.notification.rest;

import com.github.inggl.erp.notification.entities.Archive;
import com.github.inggl.erp.notification.services.ArchiveService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.UUID;

@Path("/archive")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ArchiveResource {
    @Inject
    ArchiveService archiveService;

    @GET
    public Response findAll() {
        return Response.ok(archiveService.findAll()).build();
    }

    @GET
    @Path("/{archiveId}")
    public Response findById(@PathParam("archiveId") UUID archiveId) {
        Archive archive = archiveService.findById(archiveId);

        if (archive == null) {
            return Response.noContent().build();
        }

        return Response.ok(archive).build();
    }

    @POST
    public Response create(Archive archive) {
        archiveService.create(archive);
        return Response.created(URI.create("/archive/" + archive.id)).build();
    }

    @PUT
    public Response update(Archive archive) {
        archiveService.update(archive);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{archiveId}")
    public Response delete(@PathParam("archiveId") UUID archiveId) {
        archiveService.delete(archiveId);
        return Response.ok().build();
    }
}
