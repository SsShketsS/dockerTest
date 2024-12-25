package com.example.dockerTest;

import com.example.dockerTest.docker.entity.Product;
import com.example.dockerTest.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.dockerTest.repository")
public class DockerTestApplication implements CommandLineRunner {

	@Autowired
	private ProductRepository repository;


	public static void main(String[] args) {
		SpringApplication.run(DockerTestApplication.class, args);
	}




	@Override
	public void run(String... args) throws Exception {
		//Product product1 = new Product(null, "bulochka");
		//repository.save(product1);

		final List<Product> all = repository.findAll();
		System.out.println(all);
	}
}
