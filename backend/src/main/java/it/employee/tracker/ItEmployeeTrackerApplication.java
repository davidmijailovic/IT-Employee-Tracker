package it.employee.tracker;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.logging.Logger;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class ItEmployeeTrackerApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}



	public static void main(String[] args) {
		SpringApplication.run(ItEmployeeTrackerApplication.class, args);
	}

}
