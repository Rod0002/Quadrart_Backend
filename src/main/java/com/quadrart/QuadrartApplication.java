package com.quadrart;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.quadrart.Services.StorageService.StorageService;
import com.quadrart.Storage.StorageProperties;

<<<<<<< HEAD
/*
 * Arquivo de aplicação inicial do backend.
 * Nele há funções que devem ser executas logo no ínicio do boot do back.
 */
=======
>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78
@EnableConfigurationProperties(StorageProperties.class)
@SpringBootApplication
public class QuadrartApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuadrartApplication.class, args);
	}


<<<<<<< HEAD
	/*
	 * Cria uma pasta onde as imagens serão armazenadas no boot da aplicação
	 */
=======

>>>>>>> a8e55b201a73f6e1e9db59dd7d139af492c00c78
	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.init();
		};
	}
}
