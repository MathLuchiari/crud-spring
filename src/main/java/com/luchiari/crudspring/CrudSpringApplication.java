package com.luchiari.crudspring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.luchiari.crudspring.model.Course;
import com.luchiari.crudspring.repository.CourseRepository;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean //Indica que o spring deve gerenciar o ciclo de vida deste componente
	CommandLineRunner initDatabase(CourseRepository courseRepository) {
		return args -> {
			courseRepository.deleteAll();

			Course c = new Course();
			c.setName("Angular");
			c.setCategory("front-end");

			courseRepository.save(c);

			Course c2 = new Course();
			c2.setName("Spring");
			c2.setCategory("back-end");

			courseRepository.save(c2);
		};
	}
}
