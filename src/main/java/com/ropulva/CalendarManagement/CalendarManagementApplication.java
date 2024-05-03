package com.ropulva.CalendarManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class CalendarManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalendarManagementApplication.class, args);
	}

}
