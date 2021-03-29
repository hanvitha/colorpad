package com.aliction.quarkuscolorsserver;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.operators.uni.builders.UniCreateFromKnownItem;


@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class ColorResource {

    Logger logger = LoggerFactory.getLogger(ColorResource.class);

    @ConfigProperty(name="color")
    private String color;


    @Inject
    @RestClient
    CounterService counterService;

    @POST
    // @Produces(MediaType.APPLICATION_JSON)
    @Path("confirm")
    public Uni<Response> confirm(ColorObject colorObj) {
        // logger.info(colorObj.getColor());
        if (colorObj.getColor().equals(color)){
            Uni<Response> counterResponse = counterService.Count(colorObj);
            // if (counterResponse.getStatus() == HttpStatus.SC_OK){
            //         logger.info("Counter OK");
            //         logger.info(counterResponse.readEntity(String.class));
            // }else{
            //         logger.error("Counter Not OK");
            //         logger.error("Couldn't connet to counter service");
            // }
            counterResponse.subscribe().with(
                item -> {
                    logger.info("Counter OK");
                    logger.info(item.readEntity(String.class));
                     }, 
                error -> {
                    logger.error("Counter Not OK");
                    logger.error("Couldn't connet to counter service");
            });
            return new UniCreateFromKnownItem<Response>(Response.ok(new ColorObject(colorObj.getId(), colorObj.getBoardId(), "match")).build());
        }
        // throw new ColorNotFoundException("Color is not correct");
        return new UniCreateFromKnownItem<Response>(Response.status(HttpStatus.SC_NOT_FOUND).build());
    }

    @POST
    @Path("/count")
    public Uni<String> count(){
        logger.info("Logged");
        return new UniCreateFromKnownItem<String>("Hola Counter");

    }
}