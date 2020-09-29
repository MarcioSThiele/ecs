package com.example.demo;

import com.amazonaws.services.ecs.AmazonECS;
import com.amazonaws.services.ecs.AmazonECSClientBuilder;
import com.amazonaws.services.ecs.model.DescribeTasksRequest;
import com.amazonaws.services.ecs.model.DescribeTasksResult;
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

		//ListTaskDefinitionFamiliesRequest request = new ListTaskDefinitionFamiliesRequest();
		//ListTaskDefinitionFamiliesResult response = client.listTaskDefinitionFamilies(request);
		//LOGGER.info("INFO -> " + response.toString());

		//ListContainerInstancesRequest listContainerInstancesRequest = new ListContainerInstancesRequest();
		//listContainerInstancesRequest.setCluster("ecs-cluster-tef");
		//ListContainerInstancesResult listTaskDefinitionFamiliesResult = client.listContainerInstances(listContainerInstancesRequest);
		//LOGGER.info("INFO -> " + listTaskDefinitionFamiliesResult.toString());

		//DescribeContainerInstancesRequest describeContainerInstancesRequest = new DescribeContainerInstancesRequest();
		//describeContainerInstancesRequest.setCluster("ecs-cluster-tef");

		DescribeTasksRequest describeTasksRequest = new DescribeTasksRequest();
		describeTasksRequest.setCluster("ecs-cluster-tef");

		DescribeTasksResult describeTasksResult = client.describeTasks(describeTasksRequest);

		//List<Task> list = describeTasksResult.getTasks();

		LOGGER.info("TESTE -> " + describeTasksResult.toString());

	}
}
