package com.aliction.springcolorsserver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/")
public class ColorResource {

Logger logger = LoggerFactory.getLogger(ColorResource.class);
    private ObjectMapper mapper = new ObjectMapper();

    @Value("${COLOR}") 
    private String color;

    // @Autowired
    // private Environment env;
    // String color = env.getProperty("color");

    @Autowired
    private WebClient client;


    @PostMapping(
        consumes = "application/json",
        produces = "application/json",
        path = "confirm")
    @ResponseBody
    public ColorObject ColorConfirm(@RequestBody ColorObject colorObj) throws JsonProcessingException{
        // logger.info("Color Confirm");
        // client = WebClient.create(counterURL);
        logger.info("Received : " + mapper.writeValueAsString(colorObj));
        if (colorObj.getColor().equals(this.color)){
            // logger.info(counterURL);

            Mono<String> counterResp = this.client.post().uri("/count/"+this.color).bodyValue(colorObj).header("Content-Type", "application/json")
            .exchangeToMono(response -> {
                if(response.statusCode() != HttpStatus.OK){
                //     logger.info("Counter OK");
                // }else{                    
                    logger.warn("Counter service is down");
                    return null;
                }
                return response.bodyToMono(String.class);
            });
            // logger.info(counterResp.toString());
            counterResp.onErrorReturn("Couldn't connect to counter service").subscribe(item -> {logger.info(item + " is Counted");});
            return new ColorObject(colorObj.getId(), colorObj.getSquareId(), colorObj.getBoardId(), "match");
        }else{
            logger.warn("The service will only confirm " + color + " but received " + colorObj.getColor());
            throw new ColorNotFoundException("The incoming color " + colorObj.getColor() + " is different from the configured server color " + color);
        }
    }

    // @PostMapping(
    //     consumes = "application/json",
    //     produces = "application/json",
    //     path = "/count/red")
    // @ResponseBody
    // public ColorObject Counter(@RequestBody ColorObject colorObj){
    //     return new ColorObject(colorObj.getId(), colorObj.getBoardId(), "match");
    // }
}