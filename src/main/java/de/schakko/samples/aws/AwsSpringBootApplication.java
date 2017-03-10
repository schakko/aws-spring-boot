package de.schakko.samples.aws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@Configuration
@ComponentScan
public class AwsSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwsSpringBootApplication.class, args);
	}

	@Configuration
	public static class MyConfiguration {
	}
}
