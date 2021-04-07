package com.aliction;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import javax.ws.rs.core.MediaType;

import com.aliction.quarkuscolorsserver.ColorObject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class ColorResourceTest {

    @ConfigProperty(name = "COLOR")
    String color;

    @Test
    public void testHelloEndpoint() {
        given()
          .when().contentType(MediaType.APPLICATION_JSON)
          .body("{\"id\":0, \"boardId\":1,\"color\":\""+ color +"\"}")//"+this.color+"}")
          .post("/confirm")//.body(new ColorObject(0,1,"red"))
          .then()
             .statusCode(200);
    }

}