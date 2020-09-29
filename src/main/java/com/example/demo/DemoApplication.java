package com.example.demo;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ecs.AmazonECS;
import com.amazonaws.services.ecs.AmazonECSClientBuilder;
import com.amazonaws.services.ecs.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

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

		ListTasksRequest listTasksRequest = new ListTasksRequest();
		listTasksRequest.setCluster("ecs-cluster-tef");
		listTasksRequest.setServiceName("service-depositostef-acllimiteoperacional");

		ListTasksResult listTasksResult = client.listTasks(listTasksRequest);
		List<String> listString = listTasksResult.getTaskArns();

		DescribeTasksRequest describeTasksRequest = new DescribeTasksRequest();
		describeTasksRequest.setCluster("ecs-cluster-tef");
		describeTasksRequest.setTasks(listString);

		DescribeTasksResult describeTasksResult = client.describeTasks(describeTasksRequest);

		List<Task> listTask = describeTasksResult.getTasks();
		List<Container> listContainer = listTask.get(0).getContainers();

		String containerArn = listTask.get(0).getContainerInstanceArn();
		int port = listContainer.get(0).getNetworkBindings().get(0).getHostPort();

		DescribeContainerInstancesRequest describeContainerInstancesRequest = new DescribeContainerInstancesRequest();
		describeContainerInstancesRequest.setCluster("ecs-cluster-tef");
		describeContainerInstancesRequest.setContainerInstances(Arrays.asList(containerArn));

		DescribeContainerInstancesResult describeContainerInstancesResult = client.describeContainerInstances(describeContainerInstancesRequest);

		String listContainerInstance = describeContainerInstancesResult.getContainerInstances().get(0).getEc2InstanceId();

		AmazonEC2 amazonEC2 = AmazonEC2ClientBuilder.standard().build();

		DescribeInstancesRequest describeInstancesRequestEc2 = new DescribeInstancesRequest();
		describeInstancesRequestEc2.setInstanceIds(Arrays.asList(listContainerInstance));

		DescribeInstancesResult describeInstancesResult = amazonEC2.describeInstances();

		List<Reservation> reservations = describeInstancesResult.getReservations();
		String ip = reservations.get(0).getInstances().get(0).getPrivateIpAddress();

		LOGGER.info("listContainerInstance -> " + listContainerInstance);
		LOGGER.info("ip -> " + ip);
		LOGGER.info("port -> " + port);
	}
}
