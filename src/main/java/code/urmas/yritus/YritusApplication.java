package code.urmas.yritus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class YritusApplication {

	public static void main(String[] args) {

		SpringApplication.run(YritusApplication.class, args);

		System.out.println("YritusApplication is running");
	}

}
