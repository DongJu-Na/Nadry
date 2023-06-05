package com.nadeul.ndj;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.nadeul.ndj.dto.RegisterRequest;
import com.nadeul.ndj.model.Role;
import com.nadeul.ndj.service.AuthenticationService;

@SpringBootApplication
public class NdjApplication {

	public static void main(String[] args) {
		SpringApplication.run(NdjApplication.class, args);
	}
	
	
	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service
	) {
		return args -> {
			var admin = RegisterRequest.builder()
					.name("Admin")
					.email("admin@mail.com")
					.password("password")
					.role(Role.ADMIN)
					.birthDay("20220531")
					.build();
			System.out.println("Admin token: " + service.register(admin).getAccessToken());

			var manager = RegisterRequest.builder()
					.name("Manager")
					.email("manager@mail.com")
					.password("password")
					.role(Role.MANAGER)
					.birthDay("20220531")
					.build();
			System.out.println("Manager token: " + service.register(manager).getAccessToken());

		};
	}

}
