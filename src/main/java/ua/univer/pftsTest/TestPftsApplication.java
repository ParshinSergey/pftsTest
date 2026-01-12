package ua.univer.pftsTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TestPftsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestPftsApplication.class, args);
	}

}
