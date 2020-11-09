package com.babytracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class BabytrackerappApplication {

	public static void main(String[] args) {
		SpringApplication.run(BabytrackerappApplication.class, args);
		System.out.println("Babytracker Application running");
	}

}
