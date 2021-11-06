package bz.nils.dev.jmettbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class JmettbotApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(JmettbotApplication.class, args);
	}
}
