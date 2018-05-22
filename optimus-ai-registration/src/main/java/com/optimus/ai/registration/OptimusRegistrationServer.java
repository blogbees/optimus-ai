package com.optimus.ai.registration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class OptimusRegistrationServer {

	public static void main(String[] args) {
	    System.setProperty("spring.config.name", "optimus-registration-server");
		SpringApplication.run(OptimusRegistrationServer.class, args);
	}
}
