package com.rdv.appointmnt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AppointmntApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppointmntApplication.class, args);
	}

}
