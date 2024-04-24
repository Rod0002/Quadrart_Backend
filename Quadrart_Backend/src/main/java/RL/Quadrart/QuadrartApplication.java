package RL.Quadrart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "RL.Quadrart")
@EnableJpaRepositories(basePackages = "RL.Quadrart.Repository")
@EntityScan(basePackages = {"RL.Quadrart.Model"})
public class QuadrartApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(QuadrartApplication.class, args);
	}

}
