package com.aliction.quarkuscolorsserver;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import io.smallrye.mutiny.Uni;

@Path("/count")
@RegisterRestClient
public interface CounterService {

    // public Uni<Response> Count(ColorObject colorObject);
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<Response> Count(ColorObject colorObject);
    // public Response Count(ColorObject colorObject);


    
}
