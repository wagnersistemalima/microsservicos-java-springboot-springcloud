package br.com.wagner.trabalhadoreurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class TrabalhadorEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrabalhadorEurekaServerApplication.class, args);
	}

}
