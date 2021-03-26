package com.aliction.springcolorsserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class SpringColorsServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringColorsServerApplication.class, args);
	}

	@Bean
	public WebClient getClient(@Value("${counter_url}") String counterUrl){
		return WebClient.create(counterUrl);

	}

}
