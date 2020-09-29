package com.example.demo;

import com.amazonaws.services.ecs.AmazonECS;
import com.amazonaws.services.ecs.AmazonECSClientBuilder;
import com.amazonaws.services.ecs.model.DescribeTasksRequest;
import com.amazonaws.services.ecs.model.DescribeTasksResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class DemoApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		access();
	}

	public static void access(){
		AmazonECS client = AmazonECSClientBuilder.standard().build();

		DescribeTasksRequest describeTasksRequest = new DescribeTasksRequest();
		describeTasksRequest.setCluster("ecs-cluster-tef");
		describeTasksRequest.setTasks(Arrays.asList("0190504cd8a548858121e7a53976c632"));

		DescribeTasksResult describeTasksResult = client.describeTasks(describeTasksRequest);

		LOGGER.info("TESTE -> " + describeTasksResult.toString());

	}
}
