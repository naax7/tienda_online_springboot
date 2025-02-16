package org.example.tienda_online_springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TiendaOnlineSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(TiendaOnlineSpringbootApplication.class, args);
	}

}
