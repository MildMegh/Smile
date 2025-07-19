package com.welfare.Smile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class SmileApplication {
	public static void main(String[] args) {
		SpringApplication.run(SmileApplication.class, args);
	}
}
