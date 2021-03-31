package com.aliction.springcolorsserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class SpringColorsServerApplication {

	Logger logger = LoggerFactory.getLogger(SpringColorsServerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringColorsServerApplication.class, args);
	}

	@Bean
	public WebClient getClient(@Value("${COUNTER_SERVICE}") String counterUrl){
		if (counterUrl.equals("http://127.0.0.1:8080")){
			logger.warn("COUNTER_SERVICE is not defined using localhost");
		}
		return WebClient.create(counterUrl);

	}

}
