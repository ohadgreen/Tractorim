package com.acme.tracktorim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TracktorimApplication {

	public static void main(String[] args) {
		SpringApplication.run(TracktorimApplication.class, args);
	}

}
