package com.eazybytes.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import jakarta.validation.constraints.Email;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "My first Microservices APIs by Swapnil Pawar ",
				description = "first java spring boot Microservices for banking sector for client Royal Bank of Pawar",
				version = "3.0",
				contact = @Contact (
				name = "Swapnil Nana Pawar",
				email = "swapnilpr05gmail.com",
				url = "www.Royal Bank of Pawar.com"),
			license = @License(
				name= "Apache tomcat 2.0",
					url = "www.Royal Bank of Swapnil.com" )
				),
				externalDocs = @ExternalDocumentation(
						description = "outside docus will here for extra security"
				)


)

@EnableJpaAuditing(auditorAwareRef = "auditawareimpl")
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
