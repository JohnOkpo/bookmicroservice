package com.strack3are.bookms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BookmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookmsApplication.class, args);
	}

}
