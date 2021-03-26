package com.aliction.springcolorsserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class CounterClient {

    // @Value("${counter_url}")
    // private String counterURL;
    
    // @Bean
    // public WebClient GetClient(){
    //     return WebClient.create(counterURL);
    // }
    
}
