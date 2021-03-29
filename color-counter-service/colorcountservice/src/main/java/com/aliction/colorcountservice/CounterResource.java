package com.aliction.colorcountservice;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.smallrye.mutiny.Uni;

@Path("/count")
public class CounterResource {
    Logger logger = LoggerFactory.getLogger(CounterResource.class);
    
    @Inject
    io.vertx.mutiny.pgclient.PgPool client;

    @Inject
    @ConfigProperty(name = "myapp.schema.create", defaultValue = "true")
    boolean schemaCreate;



    @PostConstruct
    void config() {
        if (schemaCreate) {
            initdb();
        }
    }

    private void initdb() {
    client.query("DROP TABLE IF EXISTS squares").execute()
    .flatMap(r -> client.query("CREATE TABLE squares (id SERIAL PRIMARY KEY, boardid Number,color TEXT NOT NULL)").execute())
    .await().indefinitely();
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<String> count(ColorObject colorObj) {
        Uni<String> rowId = colorObj.save(client)
        .onItem().transform(id -> {logger.info(id.toString() + "Saved"); return id.toString();});
        return rowId;
    }
}