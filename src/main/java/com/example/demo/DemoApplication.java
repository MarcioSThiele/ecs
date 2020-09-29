package com.example.demo;

import com.amazonaws.services.ecs.AmazonECS;
import com.amazonaws.services.ecs.AmazonECSClientBuilder;
import com.amazonaws.services.ecs.model.DescribeContainerInstancesRequest;
import com.amazonaws.services.ecs.model.DescribeContainerInstancesResult;
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

		//DescribeTasksRequest describeTasksRequest = new DescribeTasksRequest();
		//describeTasksRequest.setCluster("ecs-cluster-tef");
		//describeTasksRequest.setTasks(Arrays.asList(""));
		//DescribeTasksResult describeTasksResult = client.describeTasks(describeTasksRequest);
		//List<Task> listTask = describeTasksResult.getTasks();
		//LOGGER.info("Teste: -> " + listTask.toString());

		DescribeContainerInstancesRequest describeContainerInstancesRequest = new DescribeContainerInstancesRequest();
		describeContainerInstancesRequest.setCluster("ecs-cluster-tef");
		describeContainerInstancesRequest.setContainerInstances(Arrays.asList("arn:aws:ecs:sa-east-1:875256826423:container-instance/ecs-cluster-tef/41c15f1b3311469ca70290ca718ccbae"));

		DescribeContainerInstancesResult describeContainerInstancesResult = client.describeContainerInstances(describeContainerInstancesRequest);


		LOGGER.info("Teste -> " + describeContainerInstancesResult.toString());



	}
}
