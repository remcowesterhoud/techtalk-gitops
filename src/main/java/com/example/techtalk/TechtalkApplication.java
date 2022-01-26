package com.example.techtalk;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.EnableZeebeClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeDeployment;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableZeebeClient
@ZeebeDeployment(resources = "classpath*:*.cloud-bpmn")
public class TechtalkApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechtalkApplication.class, args);
	}

	@ZeebeWorker(type = "task1")
	public void handleTask1(final JobClient jobClient, final ActivatedJob activatedJob) {
		System.out.println("Completing task ...");
		jobClient.newCompleteCommand(activatedJob.getKey())
				.send()
				.exceptionally( throwable -> { throw new RuntimeException("I broke"); });
	}

	@ZeebeWorker(type = "task2")
	public void handleTask2(final JobClient jobClient, final ActivatedJob activatedJob) {
		System.out.println("Completing task ...");
		jobClient.newCompleteCommand(activatedJob.getKey())
				.send()
				.exceptionally( throwable -> { throw new RuntimeException("I broke"); });
	}
}
