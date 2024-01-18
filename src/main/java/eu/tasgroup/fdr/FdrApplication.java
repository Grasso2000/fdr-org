package eu.tasgroup.fdr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class FdrApplication {

	public static void main(String[] args) {
		SpringApplication.run(FdrApplication.class, args);
	}

}
