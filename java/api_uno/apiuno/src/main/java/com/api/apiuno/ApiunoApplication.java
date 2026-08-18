package com.api.apiuno;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ApiunoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiunoApplication.class, args);
	}

}
