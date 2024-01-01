package com.stark3ases.bookgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class BookgatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookgatewayApplication.class, args);
	}

}
