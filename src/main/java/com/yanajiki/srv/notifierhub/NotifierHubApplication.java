package com.yanajiki.srv.notifierhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAsync
@EnableFeignClients
@EnableKafka
public class NotifierHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotifierHubApplication.class, args);
	}

}
