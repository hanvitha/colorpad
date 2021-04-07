package com.aliction.quarkuscolorsserver;

import javax.inject.Inject;
import javax.ws.rs.ConstrainedTo;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.RuntimeType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpStatus;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.operators.uni.builders.UniCreateFromKnownItem;


@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
// @ConstrainedTo(RuntimeType.SERVER)
public class ColorResource {

    Logger logger = LoggerFactory.getLogger(ColorResource.class);

    @ConfigProperty(name="COLOR")
    String color;


    @Inject
    @RestClient
    CounterService counterService;

    private ObjectMapper mapper = new ObjectMapper();

    @POST
    @Path("confirm")
    // @Produces(MediaType.TEXT_PLAIN)
    public Uni<Response> confirm(ColorObject colorObj) throws JsonProcessingException {
        logger.info("Received : " + mapper.writeValueAsString(colorObj));
        if (colorObj.getColor().equals(color)){
            Uni<Response> counterResponse = counterService.Count(colorObj, color);
            // logger.info("RESPONSE :" + counterResponse);
            // if (counterResponse.getStatus() == HttpStatus.SC_OK){
            //         logger.info("Counter OK");
            //         logger.info(counterResponse.readEntity(String.class));
            // }else{
            //         logger.error("Counter Not OK");
            //         logger.error("Couldn't connet to counter service");
            // }


            counterResponse.subscribe().with(
                item -> {
                    // logger.info("Counter OK");
                    logger.info(item.readEntity(String.class) + " is Counted");
                    // logger.info(item + " is Counted");
                     }, 
                error -> {
                    // error.printStackTrace();
                    logger.warn("Counter service is down");
                    // logger.error("Couldn't connet to counter service");
            });
            return new UniCreateFromKnownItem<Response>(Response.ok(new ColorObject(colorObj.getId(), colorObj.getSquareId(), colorObj.getBoardId(), "match")).build());

        // return colorObj;
        }
        // throw new ColorNotFoundException("Color is not correct");
        logger.warn("The service will only confirm " + color + " but received " + colorObj.getColor());
        // return colorObj;
        return new UniCreateFromKnownItem<Response>(Response.status(HttpStatus.SC_NOT_FOUND).build());
    }

    // @POST
    // @Path("/count")
    // public Uni<String> count(){
    //     logger.info("Logged");
    //     return new UniCreateFromKnownItem<String>("Counted");

    // }
}