package dev.berlinbruno.key_nest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class KeyNestApplication {

	public static void main(String[] args) {
		SpringApplication.run(KeyNestApplication.class, args);
	}

}
