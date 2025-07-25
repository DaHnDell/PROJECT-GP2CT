package kcanmin.com.gpcgpt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GpcgptApplication {
	public static void main(String[] args) {
		SpringApplication.run(GpcgptApplication.class, args);
	}
}

// Git Persistence Commit GPT = GPCGPT