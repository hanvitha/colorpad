package com.aliction.quarkuscolorsserver;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import io.smallrye.mutiny.Uni;

@Path("/count")
@RegisterRestClient //(baseUri="http://127.0.0.1:3000")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public interface CounterService {

    // public Uni<Response> Count(ColorObject colorObject);
    @POST
    @Path("/{color}")
    // @ClientHeaderParam(name= "charset", value="UTF-8")
    public Uni<Response> Count(ColorObject colorObject,@PathParam String color);
    // public Response Count(ColorObject colorObject);


    
}
