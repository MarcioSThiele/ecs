package com.example.demo;

import com.amazonaws.services.ecs.AmazonECS;
import com.amazonaws.services.ecs.AmazonECSClientBuilder;
import com.amazonaws.services.ecs.model.ListTaskDefinitionFamiliesRequest;
import com.amazonaws.services.ecs.model.ListTaskDefinitionFamiliesResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		access();
	}

	public static void access(){
		AmazonECS client = AmazonECSClientBuilder.standard().build();
		ListTaskDefinitionFamiliesRequest request = new ListTaskDefinitionFamiliesRequest();
		ListTaskDefinitionFamiliesResult response = client.listTaskDefinitionFamilies(request);
		LOGGER.info("INFO -> " + response.toString());
	}
}
