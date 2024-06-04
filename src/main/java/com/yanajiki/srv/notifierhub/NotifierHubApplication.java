package com.yanajiki.srv.notifierhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NotifierHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotifierHubApplication.class, args);
	}

}
